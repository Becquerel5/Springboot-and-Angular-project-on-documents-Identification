import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoiseDocumentComponent } from './choise-document.component';

describe('ChoiseDocumentComponent', () => {
  let component: ChoiseDocumentComponent;
  let fixture: ComponentFixture<ChoiseDocumentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChoiseDocumentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChoiseDocumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
