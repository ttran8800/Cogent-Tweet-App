import { Component, OnInit, OnDestroy } from '@angular/core';
import { DataService } from 'src/app/services/data.service';
import { FormBuilder, Validators } from '@angular/forms';
import { IUser } from 'src/app/models/user.model';
import { Subscription, take } from 'rxjs';
import { ITweetPayload } from 'src/app/payloads/tweet.payload';
import { ClockService } from 'src/app/services/clock.service';

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css']
})
export class TweetComponent implements OnInit, OnDestroy {

  currentTime?: Date;

  user: IUser | null = null;
  private subscription: Subscription = new Subscription();

  constructor(private fb: FormBuilder, private dataService: DataService,
    private clockService: ClockService) {
  }
  ngOnInit(): void {
    this.subscription.add(
      this.dataService.user$.subscribe(user => { this.user = user })
    );
    this.subscription.add(
      this.clockService.getClock().subscribe(time => {
        this.currentTime = time;
      })
    );
  }

  ngOnDestroy(): void {
      this.subscription.unsubscribe();
  }

  tweetForm = this.fb.group({
    tweetMessage: ['', [Validators.required, Validators.maxLength(50)]]
  })

  get tweetMessage() { return this.tweetForm.get('tweetMessage') }

  checkLength() {
    const maxLength = 50;
    const currentLength = this.tweetMessage?.value?.length || 0;
  }

  onSubmitHandler() {
    if (this.tweetForm.valid) {
      const message = this.tweetForm.get('tweetMessage')!.value;
        this.clockService.getClock().pipe(take(1)).subscribe(currentDate => {
          const tweetPayload: ITweetPayload = {
            loginId: this.user!.loginId,
            message: message!,
            date: currentDate
          };
          this.dataService.createTweet(tweetPayload);
        });
    }
  }

}
