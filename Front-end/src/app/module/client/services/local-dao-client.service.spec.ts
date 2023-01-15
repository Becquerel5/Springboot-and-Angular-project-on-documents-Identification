import { TestBed } from '@angular/core/testing';

import { LocalDaoClientService } from './local-dao-client.service';

describe('LocalDaoClientService', () => {
  let service: LocalDaoClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LocalDaoClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
