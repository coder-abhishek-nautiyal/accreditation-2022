import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { of, throwError } from 'rxjs';
import { RegisterUser } from 'src/app/models/register-user';
import { RegisterComponent } from 'src/app/register/register.component';
import { RestTemplateService } from '../rest-template.service';

import { RegisterService } from './register.service';

describe('RegisterService', () => {
  let registerService: RegisterService;
  let component : RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let restTemplateService : RestTemplateService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations:[RegisterComponent],
      imports:[HttpClientTestingModule,ReactiveFormsModule,
        FormsModule]
    }).compileComponents();

    registerService = TestBed.inject(RegisterService);
    restTemplateService = TestBed.inject(RestTemplateService);
    fixture = TestBed.createComponent(RegisterComponent);
    component= fixture.componentInstance;
    fixture.detectChanges();
  
  });

  it('should be created', () => {
    expect(registerService).toBeTruthy();
  });

  it('should call registerUser() from service with success', () => {
    let response : RegisterUser[]|any;
    let request =new RegisterUser('abhi','test@test.com','password123');
    spyOn(restTemplateService,'postForEntity').and.returnValue(of(response));
    registerService.registerUser(request);
    expect(component.register()).toEqual(response);
  });


});
