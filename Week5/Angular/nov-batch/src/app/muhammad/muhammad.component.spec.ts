import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MuhammadComponent } from './muhammad.component';

describe('MuhammadComponent', () => {
  let component: MuhammadComponent;
  let fixture: ComponentFixture<MuhammadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MuhammadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MuhammadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
