package local.noto.tasklist.dtos

data class TaskResponseDto(
    val id: Long,
    var title: String,
    var description: String,
    var priority: Int,
    var isCompleted: Boolean,
)

data class CreateTaskRequestDto(
    var title: String,
    var description: String,
    var priority: Int
)

data class UpdateTaskRequestDto(
    var title: String,
    var description: String,
    var priority: Int,
    var isCompleted: Boolean
)