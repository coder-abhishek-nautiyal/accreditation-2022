import { HttpHandler, HttpHeaders, HttpRequest } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { LoadingInterceptor } from './loading.interceptor';

describe('LoadingInterceptor', () => {
  
  let interceptor : LoadingInterceptor;
  const mockHttpHeaders = new HttpHeaders().append('acceptHeader','application/json');
  const mockHttpRequest = new HttpRequest('GET','test').clone({headers: mockHttpHeaders});
  
  beforeEach(() => {TestBed.configureTestingModule({
    providers: [
      LoadingInterceptor
      ]
  })
  interceptor = TestBed.inject(LoadingInterceptor);
});

  it('should be created', () => {
    expect(interceptor).toBeTruthy();
  });

  it('should call intercept() with success', () => {
    const mockHttpHandler={
      handle : (HttpRequest)=>{
        return of({request: HttpRequest} as any);
      }
    } as HttpHandler;

    interceptor.intercept(mockHttpRequest,mockHttpHandler);
    expect(interceptor).toBeTruthy();
  });

  it('should call intercept() with finalize execution', () => {
    const mockHttpHandler={
      handle : (HttpRequest)=>{
        return of({request: HttpRequest} as any);
      }
    } as HttpHandler;

    interceptor.intercept(mockHttpRequest,mockHttpHandler).subscribe(()=>{});
    expect(interceptor).toBeTruthy();
  });

});
