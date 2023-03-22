import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { LoginComponent } from 'src/app/login/login.component';
import { LoginUser } from 'src/app/models/login-user';
import { RestTemplateService } from '../rest-template.service';

import { LoginService } from './login.service';

describe('LoginService', () => {
  let loginService: LoginService;
  let restTemplateService : RestTemplateService;
  let component : LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;


  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations:[LoginComponent],
      imports:[HttpClientTestingModule,ReactiveFormsModule,
        FormsModule,RouterTestingModule]

    });
    loginService = TestBed.inject(LoginService);
    restTemplateService = TestBed.inject(RestTemplateService);
    fixture = TestBed.createComponent(LoginComponent);
    component= fixture.componentInstance;
    fixture.detectChanges();

  });

  it('should be created', () => {
    expect(loginService).toBeTruthy();
  });

  
  it('should call validateLogin() from service', () => {
    let response = {"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEyMzQ1Iiwicm9sZSI6IlJPTEVfQURNSU4iLCJleHAiOjE2Nzk0Mzc0MzQsImlhdCI6MTY3OTQzNzEzNCwiZW1haWwiOiJhZG1pbjEyMzQ1QGFkbWluLmNvbSIsInVzZXJuYW1lIjoiYWRtaW4xMjM0NSJ9.8JQzj2m1wP0A0oS8jIzai6p3CxH9z-FdOOTCHlTPB30Uf9d18_SEvWXs9gh4yFhvd12Dwj70mObFNiYI55yuyQ"};
    let request =new LoginUser('test@test.com','password123');
    let spy=spyOn(restTemplateService,'postForEntity').and.returnValue(of(response));
    loginService.validateLogin(request);
    expect(spy).toHaveBeenCalled();
  });


});
