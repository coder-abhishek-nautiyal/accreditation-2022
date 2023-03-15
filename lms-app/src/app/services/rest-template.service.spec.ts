import { TestBed } from '@angular/core/testing';

import { RestTemplateService } from './rest-template.service';

describe('RestTemplateService', () => {
  let service: RestTemplateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestTemplateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
