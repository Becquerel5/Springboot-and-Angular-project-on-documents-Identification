import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListBuildDocumentComponent } from './list-build-document.component';

describe('ListBuildDocumentComponent', () => {
  let component: ListBuildDocumentComponent;
  let fixture: ComponentFixture<ListBuildDocumentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListBuildDocumentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListBuildDocumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
