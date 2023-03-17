import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterUser } from '../models/register-user';
import { RegisterService } from '../services/register-service/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  userRegisterForm: FormGroup;
  isUserRegistrationSuccess = false;
  errMessage = ''

  constructor(    private fb: FormBuilder,
    private http: HttpClient,
    private registerService: RegisterService) { }

  ngOnInit(): void {

    this.userRegisterForm = this.fb.group({
      username:['',Validators.required],
      email:['',Validators.required],
      password:['',Validators.required]
    })

  }

  register(){
    const formValue = this.userRegisterForm.value
    this.registerService.registerUser(new RegisterUser(formValue.username,formValue.email,formValue.password)).subscribe(data=> {
      this.isUserRegistrationSuccess = true
    },err =>{
        this.errMessage= err.error
    })
  }


}
