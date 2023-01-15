import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewClientComponent } from './preview-client.component';

describe('PreviewClientComponent', () => {
  let component: PreviewClientComponent;
  let fixture: ComponentFixture<PreviewClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreviewClientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviewClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
