package local.noto.tasklist

import local.noto.tasklist.models.Task
import local.noto.tasklist.repositories.TaskRepository
import local.noto.tasklist.services.TaskService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.Optional

class TaskServiceTest {

    private val taskRepository: TaskRepository = mock()
    private val taskService: TaskService = TaskService(taskRepository)

    @Test
    fun `does getById return right task`() {
        // Arrange
        val fakeTask = Task(id = 1L, title = "First Task", description = "Hello World", priority = 1, isCompleted = false)
        whenever(taskRepository.findById(1L)).thenReturn(Optional.of(fakeTask))

        // Act
        val result = taskService.getById(1L)

        // Assert
        assertEquals("First Task", result.title)
        assertEquals("Hello World", result.description)
        assertEquals(1, result.priority)
        assertEquals(false, result.isCompleted)
    }
}