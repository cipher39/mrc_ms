import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  ccount = 0;
  username: string = '';
  password: string = '';

  constructor() { }

  ngOnInit(): void {
  }

  changeCount(){
    this.ccount += 1;
  }

  login(){
    if(this.username=='cipher' && this.password=='cip3'){
      alert('Login Successful');
    }else{
      alert('Login Failed');
    }
  }

}
