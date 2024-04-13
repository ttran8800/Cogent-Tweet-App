import { Component } from '@angular/core';
import { ITweet } from 'src/app/models/tweet.model';
import { DataService } from 'src/app/services/data.service';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css']
})
export class TweetComponent {
  
  //placeholder
  userHandle = 'User Handle';

  //placeholder for time
  currentTime = new Date().toLocaleTimeString();

  //placeholder for message
  message = '';

  constructor(private fb: FormBuilder){

  }

  tweetForm = this.fb.group({
    tweetMessage: ['', [Validators.required, Validators.maxLength(50)]]
  })

  get tweetMessage() {return this.tweetForm.get('tweetMessage')}

  checkLength() {
    const maxLength = 50;
    const currentLength = this.tweetMessage?.value?.length || 0;
    // if (currentLength === maxLength) {
    //   this.showWarning = true;  // You can use this to trigger any other UI changes.
    // } else {
    //   this.showWarning = false;
    // }
  }
}
