package local.noto.tasklist.services

import local.noto.tasklist.dtos.CreateTaskRequestDto
import local.noto.tasklist.dtos.TaskResponseDto
import local.noto.tasklist.dtos.TransferTaskRequestDto
import local.noto.tasklist.dtos.UpdateTaskRequestDto
import local.noto.tasklist.dtos.mapper.toEntity
import local.noto.tasklist.dtos.mapper.toResponseDto
import local.noto.tasklist.exceptions.ResourceNotFoundException
import local.noto.tasklist.models.Task
import local.noto.tasklist.repositories.CategoryRepository
import local.noto.tasklist.repositories.TaskRepository
import local.noto.tasklist.repositories.UserRepository
import local.noto.tasklist.security.CurrentUserProvider
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository,
    private val currentUserProvider: CurrentUserProvider
) {

    fun getAll(): List<TaskResponseDto> =
        taskRepository.findAllByOwner(currentUserProvider.getCurrentUser()).map { it.toResponseDto() }

    fun getById(id: Long): TaskResponseDto =
        findOwnedTaskOrThrow(id).toResponseDto()

    fun create(dto: CreateTaskRequestDto): TaskResponseDto {
        val category = categoryRepository.findById(dto.categoryId)
            .orElseThrow { ResourceNotFoundException("Category with ID ${dto.categoryId} not found") }

        return taskRepository.save(dto.toEntity(category, currentUserProvider.getCurrentUser())).toResponseDto()
    }

    fun update(dto: UpdateTaskRequestDto, id: Long): TaskResponseDto {
        val existingTask = findOwnedTaskOrThrow(id)

        existingTask.title = dto.title
        existingTask.description = dto.description
        existingTask.priority = dto.priority
        existingTask.isCompleted = dto.isCompleted

        return taskRepository.save(existingTask).toResponseDto()
    }

    fun delete(id: Long) {
        taskRepository.delete(findOwnedTaskOrThrow(id))
    }

    fun transfer(id: Long, dto: TransferTaskRequestDto): TaskResponseDto {
        val task = findOwnedTaskOrThrow(id)

        val newOwner = userRepository.findByUsername(dto.username)
            .orElseThrow { ResourceNotFoundException("User '${dto.username}' not found") }

        task.owner = newOwner

        return taskRepository.save(task).toResponseDto()
    }

    private fun findOwnedTaskOrThrow(id: Long): Task =
        taskRepository.findByIdAndOwner(id, currentUserProvider.getCurrentUser())
            .orElseThrow { ResourceNotFoundException("Task with ID $id not found") }
}
