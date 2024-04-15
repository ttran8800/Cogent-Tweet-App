import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentUserTweetsComponent } from './current-user-tweets.component';

describe('CurrentUserTweetsComponent', () => {
  let component: CurrentUserTweetsComponent;
  let fixture: ComponentFixture<CurrentUserTweetsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CurrentUserTweetsComponent]
    });
    fixture = TestBed.createComponent(CurrentUserTweetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
