import { TestBed } from '@angular/core/testing';

import { AuthentificationAgentService } from './authentification-agent.service';

describe('AuthentificationAgentService', () => {
  let service: AuthentificationAgentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthentificationAgentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
