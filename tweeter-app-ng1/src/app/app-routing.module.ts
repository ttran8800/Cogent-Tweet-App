import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { RegisterComponent } from "./pages/register/register.component";
import { LoginPageComponent } from "./pages/login-page/login-page.component";
import { HomeComponent } from "./pages/home/home.component";
import { UserProfileComponent } from "./pages/user-profile/user-profile.component";


const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'login', component: LoginPageComponent },
    { path: 'user-profile', component: UserProfileComponent },
    { path: '**', redirectTo: '/home', pathMatch: 'full' }
]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule {}