import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JakeemComponent } from './jakeem.component';

describe('JakeemComponent', () => {
  let component: JakeemComponent;
  let fixture: ComponentFixture<JakeemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JakeemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JakeemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
