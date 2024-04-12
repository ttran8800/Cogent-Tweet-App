import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
<<<<<<< HEAD
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';




=======
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RegisterComponent } from './pages/register/register.component';
>>>>>>> 014fd744b21749a33fb9ac4c52b801a6b5d062c5

@NgModule({
  declarations: [
    AppComponent,
<<<<<<< HEAD
    LoginPageComponent,
=======
    NavbarComponent,
    RegisterComponent
>>>>>>> 014fd744b21749a33fb9ac4c52b801a6b5d062c5
  ],
  imports: [
    BrowserModule,
    FormsModule,
<<<<<<< HEAD
    ReactiveFormsModule
=======
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
>>>>>>> 014fd744b21749a33fb9ac4c52b801a6b5d062c5
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
