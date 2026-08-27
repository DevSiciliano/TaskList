package local.noto.tasklist.dtos.mapper

import local.noto.tasklist.dtos.RegisterRequestDto
import local.noto.tasklist.models.User

fun RegisterRequestDto.toEntity(encodedPassword: String): User =
    User(
        username = username,
        email = email,
        password = encodedPassword
    )
