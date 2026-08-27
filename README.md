# TaskList
A simple Task List / ToDo List as a showcase project written and built in Kotlin, Springboot and Gradle.

## Tech Stack
- <b>Kotlin</b> (Zulu SDK <b>21</b>)
- Spring Boot
- Spring Data JPA
- <b>PostgreSQL</b> Database
- Gradle
- React (TypeScript) frontend, see [`frontend/`](frontend)

## API Endpoints

### Tasks
| Methode | Pfad               | Beschreibung            |
|---------|---------------------|-------------------------|
| GET     | `/api/tasks`        | Get all tasks           |
| GET     | `/api/tasks/{id}`   | Get a task by id        |
| POST    | `/api/tasks`        | Create a new Task       |
| PUT     | `/api/tasks/{id}`   | Update an existing task |
| DELETE  | `/api/tasks/{id}`   | Delete a task           |
| POST    | `/api/tasks/{id}/transfer` | transfer task to diffrent user |

### Category
| Methode | Pfad              | Beschreibung            |
|---------|-------------------|-------------------------|
| GET     | `/api/category`   | Get all categroies           |
| GET     | `/api/category/{id}` | Get a category by id        |
| POST    | `/api/category`      | Create a new catgeory       |
| PUT     | `/api/category/{id}` | Update an existing category |
| DELETE  | `/api/category/{id}` | Delete a category           |

## Database

### PostgreSQL
This project uses PostgreSQL. For local development, start it via Docker Compose:

```
docker compose up -d
```

This starts a Postgres instance on `localhost:5432` with database `tasklist`, user `tasklist` and password `tasklist` (see `docker-compose.yml`). The connection details are configured in `src/main/resources/application.properties`. Tables are created/updated automatically on startup via `spring.jpa.hibernate.ddl-auto=update`.

## Deployment

The app itself is also part of `docker-compose.yml` and can be built and run in a container via the included `Dockerfile`:

```
docker compose up -d --build
```

This builds the app image, starts Postgres, waits until it's healthy, and then starts the app on `localhost:8080`.

## Frontend

A minimal React + TypeScript UI lives in [`frontend/`](frontend) — login/register and viewing/creating tasks. See [`frontend/README.md`](frontend/README.md) for setup.

```
cd frontend
npm install
npm run dev
```

Runs on `http://localhost:5173`.

## Roadmap
- [x] Create Task logic to view, create, change and delete tasks
- [x] Validating the task DTO (`@Valid, Bean Validation`)
- [x] Global exception handling with `@ControllerAdvice`
- [x] Unit and integration tests (JUnit, Mock)
- [x] Create Categories (like tasks) to sort tasks and create `findByCategory()`
- [x] Create User for login and registration
- [x] Make tasks exchangeable between users
- [x] Swagger/OpenAPI Documentation
- [x] Dockerfile for easy deployment
