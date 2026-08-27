import { createContext, useContext, useMemo, useState, type ReactNode } from 'react'
import * as authApi from '../api/auth'

interface AuthState {
  token: string | null
  username: string | null
}

interface AuthContextValue extends AuthState {
  login: (email: string, password: string) => Promise<void>
  register: (username: string, email: string, password: string) => Promise<void>
  logout: () => void
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined)

const STORAGE_KEY = 'tasklist-auth'

function loadState(): AuthState {
  const raw = localStorage.getItem(STORAGE_KEY)
  if (!raw) return { token: null, username: null }

  try {
    return JSON.parse(raw) as AuthState
  } catch {
    return { token: null, username: null }
  }
}

export function AuthProvider({ children }: { children: ReactNode }) {
  const [state, setState] = useState<AuthState>(loadState)

  const persist = (next: AuthState) => {
    setState(next)
    localStorage.setItem(STORAGE_KEY, JSON.stringify(next))
  }

  const login = async (email: string, password: string) => {
    const response = await authApi.login(email, password)
    persist({ token: response.token, username: response.username })
  }

  const register = async (username: string, email: string, password: string) => {
    const response = await authApi.register(username, email, password)
    persist({ token: response.token, username: response.username })
  }

  const logout = () => {
    persist({ token: null, username: null })
  }

  const value = useMemo(
    () => ({ ...state, login, register, logout }),
    [state],
  )

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth(): AuthContextValue {
  const context = useContext(AuthContext)
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider')
  }
  return context
}
