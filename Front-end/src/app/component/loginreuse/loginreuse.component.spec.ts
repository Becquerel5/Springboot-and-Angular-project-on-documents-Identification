import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginreuseComponent } from './loginreuse.component';

describe('LoginreuseComponent', () => {
  let component: LoginreuseComponent;
  let fixture: ComponentFixture<LoginreuseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginreuseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginreuseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
