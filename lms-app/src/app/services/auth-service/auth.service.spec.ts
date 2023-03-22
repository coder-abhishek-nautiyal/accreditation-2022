import { TestBed } from '@angular/core/testing';

import { AuthService } from './auth.service';

describe('AuthService', () => {
  let service: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call logout()', () => {
    service.logout()
    expect(service).toBeTruthy();
  });

  it('should call init() without token', () => {
    service.init();
    expect(service).toBeTruthy();
  });


  it('should call init() with token', () => {
    spyOnProperty(service,'access_token','get').and.returnValue("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjEyMzQ1Iiwicm9sZSI6IlJPTEVfQURNSU4iLCJleHAiOjE2Nzk0Mzc0MzQsImlhdCI6MTY3OTQzNzEzNCwiZW1haWwiOiJhZG1pbjEyMzQ1QGFkbWluLmNvbSIsInVzZXJuYW1lIjoiYWRtaW4xMjM0NSJ9.8JQzj2m1wP0A0oS8jIzai6p3CxH9z-FdOOTCHlTPB30Uf9d18_SEvWXs9gh4yFhvd12Dwj70mObFNiYI55yuyQ");
    service.init();
    expect(service).toBeTruthy();
  });


  it('should call setRoleAccess() with user role', () => {
    service.setRoleAccess('ROLE_USER');
    expect(service).toBeTruthy();
  });

  it('should call validateTokenExpiry() without token', () => {
    spyOnProperty(service,'access_token','get').and.returnValue(null);
    service.validateTokenExpiry();
    expect(service).toBeTruthy();
  });

  it('should call setRoleAccess() with token', () => {

    const currentTime=Math.floor(Date.now()/1000);
    const token=btoa('{"sub":"admin12345","role":"ROLE_ADMIN","exp":'+(currentTime+1000)+',"iat":1679465868,"email":"admin12345@admin.com","username":"admin12345"}')
    service.access_token=`'eyJhbGciOiJIUzUxMiJ9.${token}.-7ruHMRMMR1IGKQnGEip3uIg0hmTXB7OYm_iUqWCNfFZ_0fyyEauU2o--AiC9Zk8KuZP72YTiiBeKc_p0EZP0A'`;
    service.validateTokenExpiry();
    expect(service).toBeTruthy();
  });

});
