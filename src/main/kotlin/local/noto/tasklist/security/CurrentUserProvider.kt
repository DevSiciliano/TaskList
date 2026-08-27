package local.noto.tasklist.security

import local.noto.tasklist.exceptions.ResourceNotFoundException
import local.noto.tasklist.models.User
import local.noto.tasklist.repositories.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class CurrentUserProvider(
    private val userRepository: UserRepository
) {

    fun getCurrentUser(): User {
        val email = SecurityContextHolder.getContext().authentication?.name
            ?: throw ResourceNotFoundException("No authenticated user found")

        return userRepository.findByEmail(email)
            .orElseThrow { ResourceNotFoundException("Authenticated user not found") }
    }
}
