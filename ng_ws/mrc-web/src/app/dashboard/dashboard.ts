import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { UserResponse } from '../models/auth.model';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {
  userResponse: UserResponse | null = null;
  fullName: string = '';

  constructor(private authService: AuthService) { }
  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(
      (response: UserResponse) => {
        this.userResponse = response;
        this.fullName = response.fullName;
      },
      (error) => {
        console.error('Error fetching user data:', error);
      }
    );
  }
}
