import { TestBed } from '@angular/core/testing';

import { LocalDaoService } from './local-dao.service';

describe('LocalDaoService', () => {
  let service: LocalDaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LocalDaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
