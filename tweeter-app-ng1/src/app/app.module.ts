import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RegisterComponent } from './pages/register/register.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { HttpTokenLoader } from './utils/HttpTokenLoader.util';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import { TweetComponent } from './components/tweet/tweet.component';
import { UserTweetsComponent } from './components/user-tweets/user-tweets.component';
import { TimeAgoPipe } from './pipe/timeAgo.pipe';
import { AllUserTweetsComponent } from './pages/all-user-tweets/all-user-tweets.component';
import { CurrentUserTweetsComponent } from './pages/current-user-tweets/current-user-tweets.component';
import { TweetPageComponent } from './pages/tweet-page/tweet-page.component';
import { TweetReplyListComponent } from './components/tweet-reply-list/tweet-reply-list.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    RegisterComponent,
    LoginPageComponent,
    HomeComponent,
    HeaderComponent,
    UserProfileComponent,
    TweetComponent,
    UserTweetsComponent,
    TimeAgoPipe,
    AllUserTweetsComponent,
    CurrentUserTweetsComponent,
    TweetPageComponent,
    TweetReplyListComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpTokenLoader, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
