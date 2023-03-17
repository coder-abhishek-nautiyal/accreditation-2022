import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginUser } from '../models/login-user';
import { AuthService } from '../services/auth-service/auth.service';
import { LoginService } from '../services/login-service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  userLoginForm : FormGroup
  message = '';


  constructor(private fb: FormBuilder,
    private loginService: LoginService,
    private router:Router,
    private authService: AuthService) { }

  ngOnInit(): void {
    this.userLoginForm = this.fb.group({
      email:['',Validators.required],
      password:['',Validators.required],
    })
  }

  login(){
    const formValue = this.userLoginForm.value
    this.loginService.validateLogin(new LoginUser(formValue.email,formValue.password)).subscribe(data=> {
     console.log(data);
     this.authService.extractUserDetails(data.token);
      this.router.navigate(['/'])
    },err=>{
      this.message='Wrong username or password!!'
      console.log(err);
    })
  }


}
