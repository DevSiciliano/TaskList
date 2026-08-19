package local.noto.tasklist.controllers

import local.noto.tasklist.models.Task
import local.noto.tasklist.services.TaskService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tasks")
class TaskController(
    private val TaskService: TaskService
) {

    @GetMapping()
    fun findAll(): List<Task> =
        TaskService.getAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Task =
        TaskService.getById(id)

    @PostMapping
    fun create(@RequestBody task: Task): Task =
        TaskService.create(task)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody task: Task) =
        TaskService.update(task, id)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        TaskService.delete(id)
}