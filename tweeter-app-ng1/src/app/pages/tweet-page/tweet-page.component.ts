import { Component, OnInit } from '@angular/core';
import { ITweet } from 'src/app/models/tweet.model';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { ITweetPayload } from 'src/app/payloads/tweet.payload';
import { ClockService } from 'src/app/services/clock.service';
import { map, switchMap, take } from 'rxjs';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-tweet-page',
  templateUrl: './tweet-page.component.html',
  styleUrls: ['./tweet-page.component.css']
})
export class TweetPageComponent{
  tweet: ITweet;

  isReplyMessageBox: boolean = false;

  constructor(private router: Router, private fb: FormBuilder,
        private clockService: ClockService, private dataService: DataService) 
  {
    const navigation = this.router.getCurrentNavigation();
    this.tweet = navigation?.extras.state?.['tweet'] as ITweet;
    if (!this.tweet) {
      throw new Error('Tweet data must be provided');
    }
  }

  tweetReplyForm = this.fb.group({
    replyMessage: ['', Validators.required]
  })

  get replyMessage() { return this.tweetReplyForm.get('replyMessage') }

  onReplyBtnClick() {
    this.isReplyMessageBox = true;
  }

  onCancelBtnClick() {
    this.isReplyMessageBox = false;
    this.tweetReplyForm.reset();
  }

  checkLength() {
    const maxLength = 50;
    const currentLength = this.replyMessage?.value?.length || 0;
  }

  onSubmitBtnClick() {
    if (this.tweetReplyForm.valid) {
      const message = this.tweetReplyForm.get('replyMessage')!.value;

      this.dataService.user$.pipe(
        take(1),
        switchMap(user => {
          return this.clockService.getClock().pipe(
            take(1),
            map(currentDate => {
              const replyTweet: ITweetPayload = {
                tweetId: this.tweet.tweetId,
                loginId: user!.loginId,
                message: message!,
                date: currentDate
              };
              return replyTweet;
            })
          );
        })).subscribe({
          next: (replyTweet) => {
            this.dataService.createTweetReply(replyTweet);
            this.isReplyMessageBox = false;
          },
          error: (error) => {
            console.log(error.message)
          }
        });
    }
  }
}
