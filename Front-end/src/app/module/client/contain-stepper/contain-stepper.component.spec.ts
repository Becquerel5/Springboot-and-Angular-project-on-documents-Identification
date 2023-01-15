import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContainStepperComponent } from './contain-stepper.component';

describe('ContainStepperComponent', () => {
  let component: ContainStepperComponent;
  let fixture: ComponentFixture<ContainStepperComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContainStepperComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContainStepperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
