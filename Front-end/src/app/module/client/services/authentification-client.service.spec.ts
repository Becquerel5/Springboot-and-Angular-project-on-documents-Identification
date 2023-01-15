import { TestBed } from '@angular/core/testing';

import { AuthentificationClientService } from './authentification-client.service';

describe('AuthentificationClientService', () => {
  let service: AuthentificationClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthentificationClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
