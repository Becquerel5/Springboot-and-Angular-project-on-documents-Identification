import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewAgentComponent } from './preview-agent.component';

describe('PreviewAgentComponent', () => {
  let component: PreviewAgentComponent;
  let fixture: ComponentFixture<PreviewAgentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreviewAgentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviewAgentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
