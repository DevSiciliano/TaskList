import { apiFetch } from './client'
import type { Category } from '../types'

export function getCategories(token: string): Promise<Category[]> {
  return apiFetch<Category[]>('/api/category', {}, token)
}
