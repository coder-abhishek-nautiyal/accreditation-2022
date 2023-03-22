import { TestBed } from '@angular/core/testing';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthService } from '../services/auth-service/auth.service';

import { AuthGuardService } from './auth-guard.service';

describe('AuthGuardService', () => {
  let service: AuthGuardService;
const dummyRouter={} as ActivatedRouteSnapshot;
const state ={} as RouterStateSnapshot;
let authService : AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[RouterTestingModule]

    });
    service = TestBed.inject(AuthGuardService);
    authService = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call canActivate without login ', () => {
    service.canActivate(dummyRouter,state);
    expect(service).toBeTruthy();
  });

  it('should call canActivate with login ', () => {
    authService.isUserLoggedIn=true;
    service.canActivate(dummyRouter,state);
    expect(service).toBeTruthy();
  });

});
