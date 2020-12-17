import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JasonComponent } from './jason.component';

describe('JasonComponent', () => {
  let component: JasonComponent;
  let fixture: ComponentFixture<JasonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JasonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JasonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
