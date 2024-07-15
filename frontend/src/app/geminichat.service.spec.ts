import { TestBed } from '@angular/core/testing';

import { GeminichatService } from './geminichat.service';

describe('GeminichatService', () => {
  let service: GeminichatService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GeminichatService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
