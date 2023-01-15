import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BreadcrumbAgentComponent } from './breadcrumb-agent.component';

describe('BreadcrumbAgentComponent', () => {
  let component: BreadcrumbAgentComponent;
  let fixture: ComponentFixture<BreadcrumbAgentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BreadcrumbAgentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BreadcrumbAgentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
