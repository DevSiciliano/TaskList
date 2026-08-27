package local.noto.tasklist.repositories

import local.noto.tasklist.models.Task
import local.noto.tasklist.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface TaskRepository : JpaRepository<Task, Long> {
    fun findAllByOwner(owner: User): List<Task>
    fun findByIdAndOwner(id: Long, owner: User): Optional<Task>
}
