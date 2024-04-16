import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IUser } from '../models/user.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { ITweetPayload } from '../payloads/tweet.payload';
import { ITweet } from '../models/tweet.model';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private userSubject = new BehaviorSubject<IUser | null>(null);
  public user$: Observable<IUser | null> = this.userSubject.asObservable();

  private allUserTweetSubject = new BehaviorSubject<ITweet[] | null>(null);
  public allUserTweet$: Observable<ITweet[] | null> = this.allUserTweetSubject.asObservable();

  private tweetRepliesSubject = new BehaviorSubject<ITweet[] | null>(null);
  public tweetReplies$: Observable<ITweet[] | null> = this.tweetRepliesSubject.asObservable();

  constructor(private http: HttpClient) {
    if(localStorage.getItem('token')) {
      this.getUser();
    }
  }

  private BASE_URL = 'http://localhost:8000/api/v1.0/tweets';

  getUser(): void {
    this.http.get<IUser>(`${this.BASE_URL}/users/getUser`).subscribe({
      next: (user) => {
        this.userSubject.next(user); this.getAllUserTweet();
      },
      error: (error) => console.log('Error fetching user:', error)
    });
  }

  getAllUserTweet(): void {
    this.http.get<ITweet[]>(`${this.BASE_URL}/users/all`).subscribe({
      next: (user) => this.allUserTweetSubject.next(user),
      error: (error) => console.log('Error fetching all user tweets:', error)
    });
  }

  updateUser(user: IUser): void {
    this.http.put<IUser>(`${this.BASE_URL}/users`, user).subscribe({
      next: (updatedUser) => this.userSubject.next(updatedUser),
      error: (error) => console.log('Error updating user:', error)
    });
  }

  createTweet(tweet: ITweetPayload): void {
    this.http.post<IUser>(`${this.BASE_URL}/${tweet.loginId}/add`, tweet, { observe: 'response' }).subscribe({
      next: (response) => {
        console.log('Full HTTP response:', response); // Log the full HTTP response
        this.userSubject.next(response.body);
        console.log('Tweet created and user updated:', response.body);
        this.getAllUserTweet();
      },
      error: (error) => {
        console.error('Error creating tweet:', error);
      }
    });
  }

  createTweetReply(reply: ITweetPayload): void {
    const parentId = localStorage.getItem('parentTweetId');
    this.http.post<IUser>(`${this.BASE_URL}/${reply.loginId}/reply/${reply.tweetId}`, reply, { observe: 'response' }).subscribe({
      next: (response) => {
        this.userSubject.next(response.body);
        this.fetchAndUpdateReplies(`${parentId}`);
        this.getAllUserTweet();
      },
      error: (error) => {
        console.error('Error creating tweet:', error);
      }
    });
  }

  getAllTweetReplies(parentTweet: string): Observable<ITweet[]> {
    return this.http.get<ITweet[]>(`${this.BASE_URL}/allReplies/${parentTweet}`)
  }

  fetchAndUpdateReplies(parentId: string) {
    this.getAllTweetReplies(parentId).subscribe({
      next: replies => {
        this.tweetRepliesSubject.next(replies);
        console.log('updated replies:', replies);
      },
      error: error => console.error('Error fetching replies:', error)
    });
  }
}