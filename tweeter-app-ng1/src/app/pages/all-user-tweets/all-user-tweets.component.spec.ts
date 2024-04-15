import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllUserTweetsComponent } from './all-user-tweets.component';

describe('AllUserTweetsComponent', () => {
  let component: AllUserTweetsComponent;
  let fixture: ComponentFixture<AllUserTweetsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AllUserTweetsComponent]
    });
    fixture = TestBed.createComponent(AllUserTweetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
