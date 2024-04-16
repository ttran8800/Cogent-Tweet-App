import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { ITweet } from '../models/tweet.model';

@Injectable({
  providedIn: 'root'
})
export class TweetService {

  private currentTweetSubject = new BehaviorSubject<ITweet | null>(null);
  currentTweet$ = this.currentTweetSubject.asObservable();

  constructor(private router: Router) {}

  setCurrentTweet(tweet: ITweet): void {
    this.currentTweetSubject.next(tweet);
    this.router.navigate(['/tweet-page', tweet.tweetId]);
  }
}
