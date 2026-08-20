package local.noto.tasklist.repositories

import local.noto.tasklist.models.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long>