import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatisztikaComponent } from './statisztika.component';

describe('StatisztikaComponent', () => {
  let component: StatisztikaComponent;
  let fixture: ComponentFixture<StatisztikaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatisztikaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatisztikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
