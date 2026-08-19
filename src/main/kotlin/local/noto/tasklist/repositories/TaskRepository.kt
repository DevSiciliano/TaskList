package local.noto.tasklist.repositories

import local.noto.tasklist.models.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long>