import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthResponse, UserResponse } from './models/auth.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // The base URL for the authentication API
  private apiUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) { }

  // Method to send login request to the server
  login(username: string, password: string) {
    const loginRequest = { username, password };
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, loginRequest);
  }

  getCurrentUser() {
    const token = localStorage.getItem('accessToken');
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.get<UserResponse>(`${this.apiUrl}/user/me`, { headers });
  }

}