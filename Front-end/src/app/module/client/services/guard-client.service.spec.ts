import { TestBed } from '@angular/core/testing';

import { GuardClientService } from './guard-client.service';

describe('GuardClientService', () => {
  let service: GuardClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GuardClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
