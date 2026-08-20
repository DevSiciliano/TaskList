package local.noto.tasklist.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CategoryResponseDto(
    val id: Long,
    var name: String,
    var description: String,
    var slug: String
)

data class CreateCategoryRequestDto(
    @field:NotBlank(message = "Name must not be empty")
    @field:Size(max = 30, message = "Name must not exceed 30 characters")
    var name: String,

    @field:Size(max = 150, message = "Description must not exceed 150 characters")
    var description: String,

    @field:NotBlank(message = "Slug must not be empty")
    @field:Size(max = 15, message = "Slug must not exceed 15 characters")
    var slug: String
)

data class UpdateCategoryRequestDto(
    @field:NotBlank(message = "Name must not be empty")
    @field:Size(max = 30, message = "Name must not exceed 30 characters")
    var name: String,

    @field:Size(max = 150, message = "Description must not exceed 150 characters")
    var description: String,

    @field:NotBlank(message = "Slug must not be empty")
    @field:Size(max = 15, message = "Slug must not exceed 15 characters")
    var slug: String
)