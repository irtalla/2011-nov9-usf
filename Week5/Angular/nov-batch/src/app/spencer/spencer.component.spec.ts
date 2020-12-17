import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpencerComponent } from './spencer.component';

describe('SpencerComponent', () => {
  let component: SpencerComponent;
  let fixture: ComponentFixture<SpencerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SpencerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SpencerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
