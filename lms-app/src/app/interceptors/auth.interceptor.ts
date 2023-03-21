import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { catchError, Observable } from 'rxjs';
import { AuthService } from '../services/auth-service/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const accessToken = this.authService.access_token;
    const isUserLoggedIn = this.authService.isUserLoggedIn;
    if(isUserLoggedIn && accessToken){


       request = request.clone({
        setHeaders:{
          Authorization : `Bearer ${accessToken}`
        }
      })
  
    }
  

    return next.handle(request).pipe(catchError(err=>{
      if([401,403].includes(err.status) && err.statusText==='Unauthorized'){
      this.authService.logout();
      }
      throw err;
    }))


    }


  
}
