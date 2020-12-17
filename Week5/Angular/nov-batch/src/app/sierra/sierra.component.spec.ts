import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SierraComponent } from './sierra.component';

describe('SierraComponent', () => {
  let component: SierraComponent;
  let fixture: ComponentFixture<SierraComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SierraComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SierraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
