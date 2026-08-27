# TaskList Frontend

Minimal React + TypeScript UI for the TaskList API: login/register and viewing/creating tasks.

## Setup

```bash
npm install
npm run dev
```

Runs on `http://localhost:5173` and talks to the backend at `http://localhost:8080` (override via `VITE_API_BASE_URL` in a `.env.local` file).

The backend must be running (see the main [README](../README.md)) and must have CORS enabled for `http://localhost:5173` (already configured in `SecurityConfig.kt`).

Task creation needs at least one category to exist — create one via Swagger UI (`http://localhost:8080/swagger-ui/index.html`) first if none exist yet.
