import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { RegisterComponent } from "./pages/register/register.component";
import { LoginPageComponent } from "./pages/login-page/login-page.component";
import { HomeComponent } from "./pages/home/home.component";
import { UserProfileComponent } from "./pages/user-profile/user-profile.component";
import { AllUserTweetsComponent } from "./pages/all-user-tweets/all-user-tweets.component";
import { CurrentUserTweetsComponent } from "./pages/current-user-tweets/current-user-tweets.component";
import { TweetPageComponent } from "./pages/tweet-page/tweet-page.component";


const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'login', component: LoginPageComponent },
    { path: 'user-profile', component: UserProfileComponent },
    { path: 'all-user-tweets', component: AllUserTweetsComponent },
    { path: 'current-user-tweets', component: CurrentUserTweetsComponent },
    { path: 'tweet-page/:id', component: TweetPageComponent },
    { path: '**', redirectTo: '/home', pathMatch: 'full' }
]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule {}