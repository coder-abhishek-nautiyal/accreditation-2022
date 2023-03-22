import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { LoginService } from '../services/login-service/login.service';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let loginService : LoginService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports:[ReactiveFormsModule,
        FormsModule,HttpClientTestingModule,RouterTestingModule ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    loginService= TestBed.inject(LoginService)
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call login() with success', () => {
    let response = {"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEyMzQ1Iiwicm9sZSI6IlJPTEVfQURNSU4iLCJleHAiOjE2Nzk0Mzc0MzQsImlhdCI6MTY3OTQzNzEzNCwiZW1haWwiOiJhZG1pbjEyMzQ1QGFkbWluLmNvbSIsInVzZXJuYW1lIjoiYWRtaW4xMjM0NSJ9.8JQzj2m1wP0A0oS8jIzai6p3CxH9z-FdOOTCHlTPB30Uf9d18_SEvWXs9gh4yFhvd12Dwj70mObFNiYI55yuyQ"};
    let spy=spyOn(loginService,'validateLogin').and.returnValue(of(response));
    component.login();
    expect(spy).toHaveBeenCalled();
  });

  it('should call login() with error', () => {
    let response = {error:{message:"Login failed."}}
    let spy=spyOn(loginService,'validateLogin').and.returnValue(throwError(response));
    component.login();
    expect(spy).toHaveBeenCalled();
  });

});
