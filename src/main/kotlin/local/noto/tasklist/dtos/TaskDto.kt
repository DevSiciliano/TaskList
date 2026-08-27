package local.noto.tasklist.dtos

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import local.noto.tasklist.models.Category

data class TaskResponseDto(
    val id: Long,

    var title: String,
    var description: String,
    var priority: Int,
    var isCompleted: Boolean,
    var category: CategoryResponseDto
)

data class CreateTaskRequestDto(
    @field:NotBlank(message = "Title must not be empty")
    @field:Size(max = 50, message = "Title must not exceed 50 characters")
    var title: String,

    @field:Size(max = 500, message = "Description must not exceed 500 characters")
    var description: String,

    @field:Min(value = 1, message = "Priority must be at least 1")
    @field:Max(value = 10, message = "Priority must be at most 10")
    var priority: Int,

    var categoryId: Long
)

data class UpdateTaskRequestDto(
    @field:NotBlank(message = "Title must not be empty")
    @field:Size(max = 50, message = "Title must not exceed 50 characters")
    var title: String,

    @field:Size(max = 500, message = "Description must not exceed 500 characters")
    var description: String,

    @field:Min(value = 1, message = "Priority must be at least 1")
    @field:Max(value = 10, message = "Priority must be at most 10")
    var priority: Int,

    var isCompleted: Boolean,
)