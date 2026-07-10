import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusinessServices } from './business-services';

describe('BusinessServices', () => {
  let component: BusinessServices;
  let fixture: ComponentFixture<BusinessServices>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BusinessServices],
    }).compileComponents();

    fixture = TestBed.createComponent(BusinessServices);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
