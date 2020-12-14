import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EpitechProfilComponent } from './epitech-profil.component';

describe('EpitechProfilComponent', () => {
  let component: EpitechProfilComponent;
  let fixture: ComponentFixture<EpitechProfilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EpitechProfilComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EpitechProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
