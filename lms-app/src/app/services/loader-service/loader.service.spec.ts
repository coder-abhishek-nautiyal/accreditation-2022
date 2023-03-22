import { TestBed } from '@angular/core/testing';

import { LoaderService } from './loader.service';

describe('LoaderService', () => {
  let service: LoaderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoaderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call show()', () => {
    service.show();
    expect(service).toBeTruthy();
  });

  it('should call hide()', () => {
    service.hide();
    expect(service).toBeTruthy();
  });

});
