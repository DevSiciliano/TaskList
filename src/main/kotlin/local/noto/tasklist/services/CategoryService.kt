package local.noto.tasklist.services

import local.noto.tasklist.dtos.CategoryResponseDto
import local.noto.tasklist.dtos.CreateCategoryRequestDto
import local.noto.tasklist.dtos.UpdateCategoryRequestDto
import local.noto.tasklist.dtos.UpdateTaskRequestDto
import local.noto.tasklist.dtos.mapper.toEntity
import local.noto.tasklist.dtos.mapper.toResponseDto
import local.noto.tasklist.models.Category
import local.noto.tasklist.repositories.CategoryRepository
import local.noto.tasklist.exceptions.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun getAll(): List<CategoryResponseDto> =
        categoryRepository.findAll().map { it.toResponseDto() }

    fun getById(id: Long): CategoryResponseDto =
        findCategoryOrThrow(id).toResponseDto()

    fun create(dto: CreateCategoryRequestDto): CategoryResponseDto =
        categoryRepository.save(dto.toEntity()).toResponseDto()

    fun update(dto: UpdateCategoryRequestDto, id: Long): CategoryResponseDto {
        val existingCategory = findCategoryOrThrow(id)

        existingCategory.name = dto.name
        existingCategory.description = dto.description
        existingCategory.slug = dto.slug

        return categoryRepository.save(existingCategory).toResponseDto()
    }

    fun delete(id: Long) {
        if(!categoryRepository.existsById(id)) throw ResourceNotFoundException("Category with ID $id not found")
        categoryRepository.deleteById(id)
    }

    fun findCategoryOrThrow(id: Long): Category =
        categoryRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Category with ID $id Not Found") }
}