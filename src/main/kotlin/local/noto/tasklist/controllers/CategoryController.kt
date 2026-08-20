package local.noto.tasklist.controllers

import jakarta.validation.Valid
import local.noto.tasklist.dtos.CategoryResponseDto
import local.noto.tasklist.dtos.CreateCategoryRequestDto
import local.noto.tasklist.dtos.UpdateCategoryRequestDto
import local.noto.tasklist.dtos.UpdateTaskRequestDto
import local.noto.tasklist.services.CategoryService
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
@RequestMapping("/api/category")
class CategoryController(
    private val categoryService: CategoryService,
    private val taskService: TaskService
) {

    @GetMapping
    fun findAll(): List<CategoryResponseDto> =
        categoryService.getAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): CategoryResponseDto =
        categoryService.getById(id)

    @PostMapping
    fun create(@Valid @RequestBody dto: CreateCategoryRequestDto): CategoryResponseDto =
        categoryService.create(dto)

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: UpdateCategoryRequestDto): CategoryResponseDto =
        categoryService.update(dto, id)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        categoryService.delete(id)

}