package local.noto.tasklist.controllers

import jakarta.validation.Valid
import local.noto.tasklist.dtos.CreateTaskRequestDto
import local.noto.tasklist.dtos.TaskResponseDto
import local.noto.tasklist.dtos.TransferTaskRequestDto
import local.noto.tasklist.dtos.UpdateTaskRequestDto
import local.noto.tasklist.dtos.mapper.toResponseDto
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
    private val taskService: TaskService
) {

    @GetMapping()
    fun findAll(): List<TaskResponseDto> =
        taskService.getAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TaskResponseDto =
        taskService.getById(id)

    @PostMapping
    fun create(@Valid @RequestBody dto: CreateTaskRequestDto): TaskResponseDto  =
        taskService.create(dto)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: UpdateTaskRequestDto): TaskResponseDto =
        taskService.update(dto, id)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        taskService.delete(id)

    @PostMapping("/{id}/transfer")
    fun transfer(@PathVariable id: Long, @Valid @RequestBody dto: TransferTaskRequestDto): TaskResponseDto =
        taskService.transfer(id, dto)
}