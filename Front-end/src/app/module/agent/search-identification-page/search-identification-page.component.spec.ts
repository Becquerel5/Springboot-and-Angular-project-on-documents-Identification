import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchIdentificationPageComponent } from './search-identification-page.component';

describe('SearchIdentificationPageComponent', () => {
  let component: SearchIdentificationPageComponent;
  let fixture: ComponentFixture<SearchIdentificationPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchIdentificationPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchIdentificationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
