import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LydiaComponent } from './lydia.component';

describe('LydiaComponent', () => {
  let component: LydiaComponent;
  let fixture: ComponentFixture<LydiaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LydiaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LydiaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
