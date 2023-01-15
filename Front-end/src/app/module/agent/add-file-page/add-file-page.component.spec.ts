import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFilePageComponent } from './add-file-page.component';

describe('AddFilePageComponent', () => {
  let component: AddFilePageComponent;
  let fixture: ComponentFixture<AddFilePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddFilePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFilePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
