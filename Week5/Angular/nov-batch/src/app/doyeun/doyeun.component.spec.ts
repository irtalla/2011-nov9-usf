import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoyeunComponent } from './doyeun.component';

describe('DoyeunComponent', () => {
  let component: DoyeunComponent;
  let fixture: ComponentFixture<DoyeunComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoyeunComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoyeunComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
