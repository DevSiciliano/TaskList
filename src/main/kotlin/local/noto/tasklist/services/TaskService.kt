package local.noto.tasklist.services

import local.noto.tasklist.dtos.CreateTaskRequestDto
import local.noto.tasklist.dtos.TaskResponseDto
import local.noto.tasklist.dtos.UpdateTaskRequestDto
import local.noto.tasklist.dtos.mapper.toEntity
import local.noto.tasklist.dtos.mapper.toResponseDto
import local.noto.tasklist.exceptions.ResourceNotFoundException
import local.noto.tasklist.models.Task
import local.noto.tasklist.repositories.CategoryRepository
import local.noto.tasklist.repositories.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val categoryRepository: CategoryRepository
) {

    fun getAll(): List<TaskResponseDto> =
        taskRepository.findAll().map { it.toResponseDto() }

    fun getById(id: Long): TaskResponseDto =
        findTaskOrThrow(id).toResponseDto()

    fun create(dto: CreateTaskRequestDto): TaskResponseDto {
        val category = categoryRepository.findById(dto.categoryId)
            .orElseThrow { ResourceNotFoundException("Category with ID ${dto.categoryId} not found") }

        return taskRepository.save(dto.toEntity(category)).toResponseDto()
    }

    fun update(dto: UpdateTaskRequestDto, id: Long): TaskResponseDto  {
        val existingTask = findTaskOrThrow(id)

        existingTask.title = dto.title
        existingTask.description = dto.description
        existingTask.priority = dto.priority
        existingTask.isCompleted = dto.isCompleted

        return taskRepository.save(existingTask).toResponseDto()
    }

    fun delete(id: Long) {
        if(!taskRepository.existsById(id)) throw ResourceNotFoundException("Task with ID $id not found")
        taskRepository.deleteById(id)
    }

    fun findTaskOrThrow(id: Long): Task =
        taskRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Task with ID $id not found") }
}