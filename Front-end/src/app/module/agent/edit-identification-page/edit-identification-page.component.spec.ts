import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditIdentificationPageComponent } from './edit-identification-page.component';

describe('EditIdentificationPageComponent', () => {
  let component: EditIdentificationPageComponent;
  let fixture: ComponentFixture<EditIdentificationPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditIdentificationPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditIdentificationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
