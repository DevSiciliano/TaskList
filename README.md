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

## Roadmap
- [x] Create Task logic to view, create, change and delete tasks
- [ ] Validating the DTO (`@Valid, Bean Validation`)
- [ ] Unit and integration tests (JUnit, Mock)
- [ ] Global exception handling with `@ControllerAdvice`
- [ ] Create Categories (like tasks) to sort tasks and create `findByCategory()`
- [ ] Create User for login and registration
- [ ] Make tasks exchangeable between users
- [ ] Swagger/OpenAPI Documentation
- [ ] Dockerfile for easy deployment
