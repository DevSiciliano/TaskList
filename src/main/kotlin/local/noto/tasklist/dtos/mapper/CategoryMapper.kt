package local.noto.tasklist.dtos.mapper

import local.noto.tasklist.dtos.CategoryResponseDto
import local.noto.tasklist.dtos.CreateCategoryRequestDto
import local.noto.tasklist.dtos.UpdateCategoryRequestDto
import local.noto.tasklist.models.Category

fun Category.toResponseDto(): CategoryResponseDto =
    CategoryResponseDto(
        id = requireNotNull(id) { "ID must not be null" },
        name = name,
        description = description,
        slug = slug
    )

fun CreateCategoryRequestDto.toEntity(): Category =
    Category(
        name = name,
        description = description,
        slug = slug
    )

fun UpdateCategoryRequestDto.ToEntity(): Category =
    Category(
        name = name,
        description = description,
        slug = slug
    )