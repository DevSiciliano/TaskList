import { apiFetch } from './client'
import type { CreateTaskRequest, Task } from '../types'

export function getTasks(token: string): Promise<Task[]> {
  return apiFetch<Task[]>('/api/tasks', {}, token)
}

export function createTask(dto: CreateTaskRequest, token: string): Promise<Task> {
  return apiFetch<Task>(
    '/api/tasks',
    {
      method: 'POST',
      body: JSON.stringify(dto),
    },
    token,
  )
}

export function deleteTask(id: number, token: string): Promise<void> {
  return apiFetch<void>(`/api/tasks/${id}`, { method: 'DELETE' }, token)
}
