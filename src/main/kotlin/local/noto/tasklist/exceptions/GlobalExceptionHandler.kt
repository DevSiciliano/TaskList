package local.noto.tasklist.exceptions

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.MethodArgumentNotValidException

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(Exception::class)
    fun handleUnexpexedException(ex: Exception): ResponseEntity<ErrorResponse> {
        logger.error(ex.message, ex)

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"))
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFoundException(ex: ResourceNotFoundException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.message ?: "Ressource not found"))

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val fieldErrors = ex.bindingResult.fieldErrors
            .associate { it.field to (it.defaultMessage ?: "Not Valid") }

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", fieldErrors))
    }

    @ExceptionHandler(UsernameAlreadyExistsException::class)
    fun handleUsernameExists(ex: UsernameAlreadyExistsException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ErrorResponse(HttpStatus.CONFLICT.value(), ex.message ?: "Username already exists"))

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentials(ex: BadCredentialsException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Invalid username or password"))
}