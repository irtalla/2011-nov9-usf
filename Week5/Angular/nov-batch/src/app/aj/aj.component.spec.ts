import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AJComponent } from './aj.component';

describe('AJComponent', () => {
  let component: AJComponent;
  let fixture: ComponentFixture<AJComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AJComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AJComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
