package local.noto.tasklist.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import local.noto.tasklist.models.Category

@Entity(name = "tasks")
class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String,
    var description: String,
    var priority: Int,
    var isCompleted: Boolean,

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category
)