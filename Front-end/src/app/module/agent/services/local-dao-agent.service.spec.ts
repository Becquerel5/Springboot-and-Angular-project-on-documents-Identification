import { TestBed } from '@angular/core/testing';

import { LocalDaoAgentService } from './local-dao-agent.service';

describe('LocalDaoAgentService', () => {
  let service: LocalDaoAgentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LocalDaoAgentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
