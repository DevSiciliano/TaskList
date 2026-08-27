import type { ApiErrorBody } from '../types'

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL ?? 'http://localhost:8080'

export class ApiError extends Error {
  fieldErrors: Record<string, string>

  constructor(body: ApiErrorBody) {
    super(body.message)
    this.fieldErrors = body.fieldErrors
  }
}

export async function apiFetch<T>(
  path: string,
  options: RequestInit = {},
  token?: string | null,
): Promise<T> {
  const headers: Record<string, string> = {
    'Content-Type': 'application/json',
    ...(options.headers as Record<string, string>),
  }

  if (token) {
    headers['Authorization'] = `Bearer ${token}`
  }

  const response = await fetch(`${API_BASE_URL}${path}`, {
    ...options,
    headers,
  })

  const text = await response.text()
  const body = text ? JSON.parse(text) : undefined

  if (!response.ok) {
    throw new ApiError(body as ApiErrorBody)
  }

  return body as T
}
