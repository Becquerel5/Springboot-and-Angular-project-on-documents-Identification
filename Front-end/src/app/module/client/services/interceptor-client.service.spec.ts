import { TestBed } from '@angular/core/testing';

import { InterceptorClientService } from './interceptor-client.service';

describe('InterceptorClientService', () => {
  let service: InterceptorClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InterceptorClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
