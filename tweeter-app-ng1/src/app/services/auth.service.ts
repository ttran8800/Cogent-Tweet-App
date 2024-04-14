import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegisterPayload } from '../payloads/register.payload';
import { LoginPayload } from '../payloads/login.payload';
import { IUser } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private AUTH_URL = 'http://localhost:8000/api/v1.0/tweets';

  constructor(private http: HttpClient) { }
  
  register(user: IUser): Observable<HttpResponse<RegisterPayload>> {
    return this.http.post<RegisterPayload>(`${this.AUTH_URL}/register`, user, {observe: 'response'});
  }
  
  login(userPayload: any): Observable<HttpResponse<LoginPayload>> {
    return this.http.post<LoginPayload>(`${this.AUTH_URL}/login`, userPayload, { observe: 'response'});
  }
}
