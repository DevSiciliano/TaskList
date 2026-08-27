import { useEffect, useState, type FormEvent } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { ApiError } from '../api/client'
import { getTasks, createTask, deleteTask } from '../api/tasks'
import { getCategories } from '../api/categories'
import type { Category, Task } from '../types'

export default function TasksPage() {
  const { token, username, logout } = useAuth()
  const navigate = useNavigate()

  const [tasks, setTasks] = useState<Task[]>([])
  const [categories, setCategories] = useState<Category[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  const [title, setTitle] = useState('')
  const [description, setDescription] = useState('')
  const [priority, setPriority] = useState(5)
  const [categoryId, setCategoryId] = useState<number | ''>('')
  const [submitting, setSubmitting] = useState(false)

  const loadData = async () => {
    if (!token) return
    setLoading(true)
    setError(null)

    try {
      const [taskList, categoryList] = await Promise.all([
        getTasks(token),
        getCategories(token),
      ])
      setTasks(taskList)
      setCategories(categoryList)
      if (categoryList.length > 0 && categoryId === '') {
        setCategoryId(categoryList[0].id)
      }
    } catch (err) {
      setError(err instanceof ApiError ? err.message : 'Laden fehlgeschlagen')
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    loadData()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [token])

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  const handleCreate = async (event: FormEvent) => {
    event.preventDefault()
    if (!token || categoryId === '') return

    setSubmitting(true)
    setError(null)

    try {
      const task = await createTask(
        { title, description, priority, categoryId },
        token,
      )
      setTasks((prev) => [...prev, task])
      setTitle('')
      setDescription('')
      setPriority(5)
    } catch (err) {
      setError(err instanceof ApiError ? err.message : 'Task konnte nicht erstellt werden')
    } finally {
      setSubmitting(false)
    }
  }

  const handleDelete = async (id: number) => {
    if (!token) return

    try {
      await deleteTask(id, token)
      setTasks((prev) => prev.filter((t) => t.id !== id))
    } catch (err) {
      setError(err instanceof ApiError ? err.message : 'Task konnte nicht gelöscht werden')
    }
  }

  return (
    <div className="tasks-page">
      <header className="tasks-header">
        <h1>Meine Tasks</h1>
        <div>
          <span className="username">{username}</span>
          <button onClick={handleLogout}>Logout</button>
        </div>
      </header>

      {error && <p className="error">{error}</p>}

      {categories.length === 0 && !loading && (
        <p className="hint">
          Es existieren noch keine Kategorien. Lege zuerst eine über die{' '}
          <a href="http://localhost:8080/swagger-ui/index.html" target="_blank" rel="noreferrer">
            API (Swagger)
          </a>{' '}
          an, um Tasks erstellen zu können.
        </p>
      )}

      <form className="task-form" onSubmit={handleCreate}>
        <input
          placeholder="Titel"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
          maxLength={50}
        />
        <input
          placeholder="Beschreibung"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          maxLength={500}
        />
        <input
          type="number"
          min={1}
          max={10}
          value={priority}
          onChange={(e) => setPriority(Number(e.target.value))}
          title="Priorität (1-10)"
        />
        <select
          value={categoryId}
          onChange={(e) => setCategoryId(Number(e.target.value))}
          disabled={categories.length === 0}
        >
          {categories.map((c) => (
            <option key={c.id} value={c.id}>
              {c.name}
            </option>
          ))}
        </select>
        <button type="submit" disabled={submitting || categories.length === 0}>
          {submitting ? 'Speichern…' : 'Task anlegen'}
        </button>
      </form>

      {loading ? (
        <p>Lade Tasks…</p>
      ) : (
        <ul className="task-list">
          {tasks.map((task) => (
            <li key={task.id} className="task-item">
              <div>
                <strong>{task.title}</strong>{' '}
                <span className="badge">{task.category.name}</span>{' '}
                <span className="badge">Prio {task.priority}</span>
                {task.isCompleted && <span className="badge done">erledigt</span>}
                <p>{task.description}</p>
                <small>Owner: {task.owner.username}</small>
              </div>
              <button onClick={() => handleDelete(task.id)}>Löschen</button>
            </li>
          ))}
          {tasks.length === 0 && <p>Noch keine Tasks vorhanden.</p>}
        </ul>
      )}
    </div>
  )
}
