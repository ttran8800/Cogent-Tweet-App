import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IUser } from '../models/user.model';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private userSubject = new BehaviorSubject<IUser | null>(null);
  public user$: Observable<IUser | null> = this.userSubject.asObservable();

  constructor(private http: HttpClient) {
    this.getUser();
  }

  private BASE_URL = 'http://localhost:8000/api/v1.0/tweets';

  getUser(): void {
    this.http.get<IUser>(`${this.BASE_URL}/users/getUser`).subscribe({
      next: (user) => this.userSubject.next(user),
      error: (error) => console.log('Error fetching user:', error)
    });
  }

  updateUser(user: IUser): void {
    this.http.put<IUser>(`${this.BASE_URL}/users`, user).subscribe({
      next: (updatedUser) => this.userSubject.next(updatedUser),
      error: (error) => console.log('Error updating user:', error)
    });
  }

}