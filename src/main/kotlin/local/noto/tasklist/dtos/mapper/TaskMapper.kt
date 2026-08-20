package local.noto.tasklist.dtos.mapper

import local.noto.tasklist.dtos.CreateTaskRequestDto
import local.noto.tasklist.dtos.TaskResponseDto
import local.noto.tasklist.dtos.UpdateTaskRequestDto
import local.noto.tasklist.models.Task

fun Task.toResponseDto(): TaskResponseDto =
    TaskResponseDto(
        id = requireNotNull(id) { "ID must not be null" },
        title = title,
        description = description,
        priority = priority,
        isCompleted = isCompleted
    )

fun CreateTaskRequestDto.toEntity(): Task =
    Task(
        title = title,
        description = description,
        priority = priority,
        isCompleted = false
    )

fun UpdateTaskRequestDto.toEntity(): Task =
    Task(
        title = title,
        description = description,
        priority = priority,
        isCompleted = isCompleted
    )
