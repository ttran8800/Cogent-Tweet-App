import { Component } from '@angular/core';
import { EmailValidator, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IUser } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
  message = ''
  alertClass = ''
  constructor(private fb: FormBuilder, private authService: AuthService) {
   
  }
  loginForm = this.fb.group({
    usernameOrEmail: [null, Validators.required],
    password: [null,[ Validators.required, Validators.minLength(6)]]
  })
  get usernameOrEmail() {
    return this.loginForm.get('usernameOrEmail');
  }

  get password() {
    return this.loginForm.get('password');
  }



  // //onLoginHandler() {
  //   if (this.loginForm.valid) {
  //     const usernameOrEmail = this.loginForm.value.usernameOrEmail;
  //     const password = this.loginForm.value.password;
  //     const login = this.loginForm.value;

  //     console.log('Username or Email:', usernameOrEmail);
  //     console.log('Password:', password);
  //     console.log('Login:', login);
  //   }
  //}
  onLoginHandler(){
    if(this.loginForm.valid){
      const userPayload = {
      usernameOrEmail: this.loginForm.value.usernameOrEmail,
      password: this.loginForm.value.password
    }
    this.authService.login(userPayload)
    .subscribe({
      next: () => {
        this.message = "Registration Successful",
        this.alertClass = 'alert alert-success';
      },
      error: (error) => {
        this.message = "Registration failed";
        this.alertClass = 'alert alert-danger';
        console.log(error);
      }
    });
    console.log(userPayload)
  }
  }
}