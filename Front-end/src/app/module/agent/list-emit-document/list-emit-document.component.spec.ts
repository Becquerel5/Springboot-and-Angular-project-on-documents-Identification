import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListEmitDocumentComponent } from './list-emit-document.component';

describe('ListEmitDocumentComponent', () => {
  let component: ListEmitDocumentComponent;
  let fixture: ComponentFixture<ListEmitDocumentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListEmitDocumentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListEmitDocumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
