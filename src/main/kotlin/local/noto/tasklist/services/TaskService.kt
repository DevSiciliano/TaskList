package local.noto.tasklist.services

import local.noto.tasklist.models.Task
import local.noto.tasklist.repositories.TaskRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {

    fun getAll(): List<Task> =
        taskRepository.findAll()

    fun getById(id: Long): Task =
        taskRepository.findById(id)
            .orElseThrow { NoSuchElementException("Task with ID $id not found") }

    fun create(task: Task): Task =
        taskRepository.save(task)

    fun update(updated: Task, id: Long): Task {
        val existingTask = getById(id)

        existingTask.title = updated.title
        existingTask.description = updated.description
        existingTask.priority = updated.priority
        existingTask.isCompleted = updated.isCompleted

        return taskRepository.save(existingTask)
    }

    fun delete(id: Long) {
        if(!taskRepository.existsById(id)) throw NoSuchElementException("Task with ID $id not found")
        taskRepository.deleteById(id)
    }
}