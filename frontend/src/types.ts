export interface AuthResponse {
  token: string
  username: string
}

export interface Category {
  id: number
  name: string
  description: string
  slug: string
}

export interface UserSummary {
  id: number
  username: string
}

export interface Task {
  id: number
  title: string
  description: string
  priority: number
  isCompleted: boolean
  category: Category
  owner: UserSummary
}

export interface CreateTaskRequest {
  title: string
  description: string
  priority: number
  categoryId: number
}

export interface ApiErrorBody {
  statusCode: number
  message: string
  fieldErrors: Record<string, string>
  timeStamp: string
}
