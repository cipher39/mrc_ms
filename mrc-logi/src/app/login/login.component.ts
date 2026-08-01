import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { AuthResponse } from '../models/auth.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
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
