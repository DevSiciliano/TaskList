package local.noto.tasklist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskListApplication

fun main(args: Array<String>) {
    runApplication<TaskListApplication>(*args)
}
