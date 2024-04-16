import { Component, OnInit, OnDestroy } from '@angular/core';
import { DataService } from 'src/app/services/data.service';
import { Subscription } from 'rxjs';
import { ITweet } from 'src/app/models/tweet.model';
import { Router, ActivatedRoute } from '@angular/router';
import { TweetService } from 'src/app/services/tweet.service';

@Component({
  selector: 'app-all-user-tweets',
  templateUrl: './all-user-tweets.component.html',
  styleUrls: ['./all-user-tweets.component.css']
})
export class AllUserTweetsComponent implements OnInit, OnDestroy {

  tweetList: ITweet[] | null = null;


  private subscription: Subscription = new Subscription();

  constructor(private dataService: DataService,
              private router: Router,
              private tweetService: TweetService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.subscription.add(
      this.dataService.allUserTweet$.subscribe(tweet => { 
        this.tweetList = tweet;
        if (this.tweetList) {
          this.tweetList.sort((a,b) => new Date(b.date).getTime() - new Date(a.date).getTime());
        }
      })
    )
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  onTweetBoxClick(tweet: ITweet) {
      localStorage.setItem('parentTweetId', `${tweet.tweetId}`);
      this.router.navigate(['/tweet-page'], { state: { tweet:tweet } });
  }
}
