# TaskList
A simple Task List / ToDo List as a showcase project written and built in Kotlin, Springboot and Gradle.

## Tech Stack
- <b>Kotlin</b> (Zulu SDK <b>21</b>)
- Spring Boot
- Spring Data JPA
- H2 / <b>PostgreSQL</b> Database
- Gradle

## API Endpoints

### Tasks
| Methode | Pfad               | Beschreibung            |
|---------|---------------------|-------------------------|
| GET     | `/api/tasks`        | Get all tasks           |
| GET     | `/api/tasks/{id}`   | Get a task by id        |
| POST    | `/api/tasks`        | Create a new Task       |
| PUT     | `/api/tasks/{id}`   | Update an existing task |
| DELETE  | `/api/tasks/{id}`   | Delete a task           |

### Category
| Methode | Pfad              | Beschreibung            |
|---------|-------------------|-------------------------|
| GET     | `/api/category`   | Get all tasks           |
| GET     | `/api/category/{id}` | Get a task by id        |
| POST    | `/api/category`      | Create a new Task       |
| PUT     | `/api/category/{id}` | Update an existing task |
| DELETE  | `/api/category/{id}` | Delete a task           |

## Database

### H2
For development this project is using the H2 Database in memory storage.<br>
The Database URL is `jdbc:h2:mem:test` and to connect call `http://localhost:8080/h2-console`

### PostgreSQL

TODO: defined after development to imitate a productive database system.


## Roadmap
- [x] Create Task logic to view, create, change and delete tasks
- [x] Validating the task DTO (`@Valid, Bean Validation`)
- [x] Global exception handling with `@ControllerAdvice`
- [x] Unit and integration tests (JUnit, Mock)
- [x] Create Categories (like tasks) to sort tasks and create `findByCategory()`
- [x] Create User for login and registration
- [ ] Make tasks exchangeable between users
- [ ] Swagger/OpenAPI Documentation
- [ ] Dockerfile for easy deployment
