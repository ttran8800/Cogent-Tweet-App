import { Component, OnInit } from '@angular/core';
import { DataService } from 'src/app/services/data.service';
import { ITweet } from 'src/app/models/tweet.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tweet-reply-list',
  templateUrl: './tweet-reply-list.component.html',
  styleUrls: ['./tweet-reply-list.component.css']
})
export class TweetReplyListComponent implements OnInit {

  tweetReplies?: ITweet[] | null;

  constructor(private dataService: DataService, private router: Router) {
  }

  ngOnInit(): void {
    const parentId = localStorage.getItem('parentTweetId');
    if (parentId) {
      this.dataService.fetchAndUpdateReplies(parentId);
      this.dataService.tweetReplies$.subscribe(replies => {
        this.tweetReplies = replies;
        if (this.tweetReplies) {
          this.tweetReplies = this.tweetReplies.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
        }
      })
    } else {
      console.error('No parent ID found in localStorage');
    }
  }

  onTweetBoxClick(reply: ITweet) {
    localStorage.setItem('parentTweetId', `${reply.tweetId}`);
    this.router.navigate(['/tweet-page'], { state: { reply: reply } });
  }
}