import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GeminichatComponent } from './geminichat.component';

describe('GeminichatComponent', () => {
  let component: GeminichatComponent;
  let fixture: ComponentFixture<GeminichatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GeminichatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GeminichatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
