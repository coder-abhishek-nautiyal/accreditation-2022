import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Form, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { RegisterUser } from '../models/register-user';
import { RegisterService } from '../services/register-service/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  userRegisterForm: FormGroup;
  @ViewChild('ngForm') ngForm: NgForm;
  isUserRegistrationSuccess = false;
  errMessage = ''
  hide :boolean = true;


  constructor(    private fb: FormBuilder,
    private http: HttpClient,
    private registerService: RegisterService) { }

  ngOnInit(): void {

    this.userRegisterForm = this.fb.group({
      username:['',Validators.required],
      email:['',[Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\.com$")]],
      password:['',[Validators.required,Validators.minLength(8),Validators.pattern("^[a-zA-Z0-9]+$")]],
    })

  }

  register(){
    const formValue = this.userRegisterForm.value
    this.registerService.registerUser(new RegisterUser(formValue.username,formValue.email,formValue.password)).subscribe(data=> {
      this.isUserRegistrationSuccess = true
      this.errMessage='';
      this.userRegisterForm.reset('');
      this.ngForm.resetForm();
    },err =>{
        this.errMessage= err.error && (err.error.message || err.error.error);
    })
  }


}
