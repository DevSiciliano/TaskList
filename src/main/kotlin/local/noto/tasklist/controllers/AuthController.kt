package local.noto.tasklist.controllers

import jakarta.validation.Valid
import local.noto.tasklist.dtos.AuthResponseDto
import local.noto.tasklist.dtos.LoginRequestDto
import local.noto.tasklist.dtos.RegisterRequestDto
import local.noto.tasklist.services.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody dto: RegisterRequestDto): AuthResponseDto =
        authService.register(dto)

    @PostMapping("/login")
    fun login(@Valid @RequestBody dto: LoginRequestDto): AuthResponseDto =
        authService.login(dto)
}
