import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTweetsComponent } from './user-tweets.component';

describe('UserTweetsComponent', () => {
  let component: UserTweetsComponent;
  let fixture: ComponentFixture<UserTweetsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserTweetsComponent]
    });
    fixture = TestBed.createComponent(UserTweetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
