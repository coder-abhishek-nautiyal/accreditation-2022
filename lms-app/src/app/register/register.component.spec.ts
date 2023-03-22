import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { throwError } from 'rxjs';
import { RegisterService } from '../services/register-service/register.service';

import { RegisterComponent } from './register.component';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let registerService : RegisterService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterComponent ],
      imports:[ReactiveFormsModule,
        FormsModule,HttpClientTestingModule ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    registerService= TestBed.inject(RegisterService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  it('should call register() with error', () => {
    let response = {error:{message:"Registration failed."}}
    let spy=spyOn(registerService,'registerUser').and.returnValue(throwError(response));
    component.register();
    expect(spy).toHaveBeenCalled();
  });

});
