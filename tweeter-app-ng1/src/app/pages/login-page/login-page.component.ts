import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
  message = ''
  alertClass = ''
  countdown: number = 5;
  redirectMessage: string = 'Redirecting in ';
  isLogin: boolean = false;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private dataService: DataService) {

  }
  loginForm = this.fb.group({
    usernameOrEmail: ['', Validators.required],
    password: ['', [Validators.required, Validators.minLength(6)]]
  })
  get usernameOrEmail() {
    return this.loginForm.get('usernameOrEmail');
  }

  get password() {
    return this.loginForm.get('password');
  }


  onLoginHandler() {
    if (this.loginForm.valid) {
      const userPayload = {
        usernameOrEmail: this.loginForm.value.usernameOrEmail,
        password: this.loginForm.value.password
      }
      this.authService.login(userPayload).subscribe({
        next: (response) => {
          if (response.body && !response.body.error) {
            this.message = response.body.message;
            this.alertClass = 'alert alert-success';
            localStorage.setItem('token', response.body.token);
            this.loginForm.reset();
            this.isLogin = true;
            this.dataService.getUser();
            const interval = setInterval(() => {
              this.countdown--;
              if (this.countdown === 0) {
                clearInterval(interval);
                this.redirect();
                this.isLogin = false;
              }

            }, 1000)
          } else {
            this.message = response.body?.message || "Login failed.";
            this.alertClass = 'alert alert-danger';
          }
        },
        error: (error) => {
          switch (error.status) {
            case 401:
              this.message = "Invalid credentials provided.";
              break;
            case 400:
              this.message = "Bad request. Please check your input.";
              break;
            case 500:
              this.message = "Server error occurred. Try again later.";
              break;
            default:
              this.message = "Login failed due to a network or server error.";
              break;
          }
          this.alertClass = 'alert alert-danger';
          console.log(error);
        }
      });
    }
  }
  redirect() {
    this.router.navigateByUrl('/home');
  }
}