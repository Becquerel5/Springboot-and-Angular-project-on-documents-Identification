import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListIdentificationPageComponent } from './list-identification-page.component';

describe('ListIdentificationPageComponent', () => {
  let component: ListIdentificationPageComponent;
  let fixture: ComponentFixture<ListIdentificationPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListIdentificationPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListIdentificationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
