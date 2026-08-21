package local.noto.tasklist.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterRequestDto(
    @field:NotBlank(message = "Username must not be empty")
    @field:Size(min = 3, max = 30, message = "Username must be 3-30 characters")
    var username: String,

    @field:NotBlank(message = "E-Mail must not be empty")
    @field:Size(min = 3, max = 30, message = "E-Mail must be 3-30 characters")
    var email: String,

    @field:NotBlank(message = "Password must not be empty")
    @field:Size(min = 8, message = "Password must be at least 8 characters")
    var password: String,
)

data class LoginRequestDto(
    @field:NotBlank(message = "E-Mail must not be empty")
    var email: String,

    @field:NotBlank(message = "Password must not be empty")
    var password: String
)

data class AuthResponseDto(
    val token: String,
    val username: String
)