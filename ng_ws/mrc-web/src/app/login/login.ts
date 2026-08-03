import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { AuthResponse } from '../models/auth.model';


@Component({
  selector: 'app-login',
  imports: [FormsModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  ccount = 0;
  username: string = '';
  password: string = '';
  isLoggedIn: boolean = false;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  changeCount(){
    this.ccount += 1;
  }

  login(){
    this.authService.login(this.username, this.password).subscribe(
      (response: AuthResponse) => {
        localStorage.setItem('accessToken', response.accessToken);
        this.isLoggedIn = true;
      },
      (error) => {
        this.isLoggedIn = false;
      }
    );
  }
}
