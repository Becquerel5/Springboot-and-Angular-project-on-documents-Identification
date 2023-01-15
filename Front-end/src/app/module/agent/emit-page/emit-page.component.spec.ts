import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmitPageComponent } from './emit-page.component';

describe('EmitPageComponent', () => {
  let component: EmitPageComponent;
  let fixture: ComponentFixture<EmitPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmitPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmitPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
