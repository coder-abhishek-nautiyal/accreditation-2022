import { HttpHandler, HttpHeaders, HttpRequest } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { of, throwError } from 'rxjs';
import { AuthService } from '../services/auth-service/auth.service';

import { AuthInterceptor } from './auth.interceptor';

describe('AuthInterceptor', () => {
  let interceptor: AuthInterceptor 
  let authService : AuthService;
  const mockHttpHeaders = new HttpHeaders().append('acceptHeader','application/json');
  const mockHttpRequest = new HttpRequest('GET','test').clone({headers: mockHttpHeaders});
  const mockRouter = {url:'test'};
  beforeEach(() =>{ TestBed.configureTestingModule({
    providers: [
      AuthInterceptor,
      {provide: Router , useValue: mockRouter}
      ]
  });

  interceptor = TestBed.inject(AuthInterceptor);
  authService = TestBed.inject(AuthService);

});

  it('should be created', () => {
    expect(interceptor).toBeTruthy();
  });

  it('should call intercept() with success handler and without token', () => {

    const mockHttpHandler={
      handle : (HttpRequest)=>{
        return of({request: HttpRequest} as any);
      }
    } as HttpHandler;

    interceptor.intercept(mockHttpRequest,mockHttpHandler);
    expect(interceptor).toBeTruthy();
  });

  it('should call intercept() with success handler and with token', () => {
    spyOnProperty(authService,'access_token','get').and.returnValue('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEyMzQ1Iiwicm9sZSI6IlJPTEVfQURNSU4iLCJleHAiOjE2Nzk0Njc5MjMsImlhdCI6MTY3OTQ2NzYyMywiZW1haWwiOiJhZG1pbjEyMzQ1QGFkbWluLmNvbSIsInVzZXJuYW1lIjoiYWRtaW4xMjM0NSJ9.AXTUkRBjZOQR7iIYajNEBB0NVV5_T8WKbumbtu89TD5k71invT7FjEn06Hph8Qze11qv1cR6UMapSqMWTNqalQ');
    authService.isUserLoggedIn=true;
    const mockHttpHandler={
      handle : (HttpRequest)=>{
        return of({request: HttpRequest} as any);
      }
    } as HttpHandler;

    interceptor.intercept(mockHttpRequest,mockHttpHandler);
    expect(interceptor).toBeTruthy();
  });

  it('should call intercept() with error', () => {
    const mockError={status:401,statusText:'Unauthorized'};
    const mockHttpHandler={
      handle : (HttpRequest)=>{
        return throwError(mockError);
      }
    } as HttpHandler;

    interceptor.intercept(mockHttpRequest,mockHttpHandler).subscribe(null,()=>{});
    expect(interceptor).toBeTruthy();
  });

  


});
