package local.noto.tasklist

import local.noto.tasklist.dtos.CreateTaskRequestDto
import local.noto.tasklist.models.Category
import local.noto.tasklist.models.Task
import local.noto.tasklist.models.User
import local.noto.tasklist.repositories.CategoryRepository
import local.noto.tasklist.repositories.TaskRepository
import local.noto.tasklist.repositories.UserRepository
import local.noto.tasklist.security.CurrentUserProvider
import local.noto.tasklist.services.TaskService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.Optional

class TaskServiceTest {

    private val taskRepository: TaskRepository = mock()
    private val categoryRepository: CategoryRepository = mock()
    private val userRepository: UserRepository = mock()
    private val currentUserProvider: CurrentUserProvider = mock()
    private val taskService: TaskService = TaskService(taskRepository, categoryRepository, userRepository, currentUserProvider)

    private val fakeCategory = Category(id = 1L, name = "Work", description = "Work tasks", slug = "work")
    private val fakeUser = User(id = 1L, username = "max", email = "max@example.com", password = "encoded")

    @Test
    fun `does getById return right task`() {
        // Arrange
        val fakeTask = Task(id = 1L, title = "First Task", description = "Hello World", priority = 1, isCompleted = false, category = fakeCategory, owner = fakeUser)
        whenever(currentUserProvider.getCurrentUser()).thenReturn(fakeUser)
        whenever(taskRepository.findByIdAndOwner(1L, fakeUser)).thenReturn(Optional.of(fakeTask))

        // Act
        val result = taskService.getById(1L)

        // Assert
        assertEquals("First Task", result.title)
        assertEquals("Hello World", result.description)
        assertEquals(1, result.priority)
        assertEquals(false, result.isCompleted)
    }

    @Test
    fun `creates a new task`() {
        // Arrange
        val dto = CreateTaskRequestDto(title = "New Task", description = "Hello World", priority = 1, categoryId = 1L)
        val savedTask = Task(id = 42L, title = "New Task", description = "Hello World", priority = 1, isCompleted = false, category = fakeCategory, owner = fakeUser)

        whenever(currentUserProvider.getCurrentUser()).thenReturn(fakeUser)
        whenever(categoryRepository.findById(1L)).thenReturn(Optional.of(fakeCategory))
        whenever(taskRepository.save(any())).thenReturn(savedTask)

        // Act
        val result = taskService.create(dto)

        //Assert
        assertEquals("New Task", result.title)
        assertEquals("Hello World", result.description)
    }
}
