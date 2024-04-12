import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private AUTH_URL = 'http://localhost:8000/api/v1.0/tweets';

  constructor(private http: HttpClient) { }
  
  register(user: any) {
    return this.http.post(`${this.AUTH_URL}/register`, user);
  }
  login(userPayload: any){
    return this.http.post(`${this.AUTH_URL}/login`, userPayload);
  }
}
