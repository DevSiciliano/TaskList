package local.noto.tasklist.services

import local.noto.tasklist.dtos.AuthResponseDto
import local.noto.tasklist.dtos.LoginRequestDto
import local.noto.tasklist.dtos.RegisterRequestDto
import local.noto.tasklist.dtos.mapper.toEntity
import local.noto.tasklist.exceptions.UsernameAlreadyExistsException
import local.noto.tasklist.repositories.UserRepository
import local.noto.tasklist.security.JwtService
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {

    fun register(dto: RegisterRequestDto): AuthResponseDto {
        if (userRepository.existsByUsername(dto.username)) {
            throw UsernameAlreadyExistsException("Username '${dto.username}' is already taken")
        }

        if (userRepository.existsByEmail(dto.email)) {
            throw UsernameAlreadyExistsException("E-Mail '${dto.email}' is already registered")
        }

        val user = userRepository.save(dto.toEntity(passwordEncoder.encode(dto.password)!!))

        return AuthResponseDto(token = jwtService.generateToken(user.email), username = user.username)
    }

    fun login(dto: LoginRequestDto): AuthResponseDto {
        val user = userRepository.findByEmail(dto.email)
            .orElseThrow { BadCredentialsException("Invalid email or password") }

        if (!passwordEncoder.matches(dto.password, user.password)) {
            throw BadCredentialsException("Invalid email or password")
        }

        return AuthResponseDto(token = jwtService.generateToken(user.email), username = user.username)
    }
}
