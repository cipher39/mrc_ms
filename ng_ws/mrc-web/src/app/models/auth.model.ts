// src/app/models/auth.model.ts

// Defines what you send TO the server
export interface LoginRequest {
  username: string;
  password: string;
}

// Defines what you get back FROM the server
export interface AuthResponse {
  accessToken: string;
  tokenType: string;
  expiresIn: number;
  username: string;
  roles: string[];
}


// Defines the structure of the user object
export interface UserResponse {
  id: number;
  username: string;
  fullName: string;
  email: string;
  roles: string[];
}