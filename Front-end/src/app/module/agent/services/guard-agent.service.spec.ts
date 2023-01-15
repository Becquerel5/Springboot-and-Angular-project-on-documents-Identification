import { TestBed } from '@angular/core/testing';

import { GuardAgentService } from './guard-agent.service';

describe('GuardAgentService', () => {
  let service: GuardAgentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GuardAgentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
