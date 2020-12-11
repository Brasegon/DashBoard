import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DelWidgetComponent } from './del-widget.component';

describe('DelWidgetComponent', () => {
  let component: DelWidgetComponent;
  let fixture: ComponentFixture<DelWidgetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DelWidgetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DelWidgetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
