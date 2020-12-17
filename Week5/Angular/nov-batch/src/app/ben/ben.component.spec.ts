import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BenComponent } from './ben.component';

describe('BenComponent', () => {
  let component: BenComponent;
  let fixture: ComponentFixture<BenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
