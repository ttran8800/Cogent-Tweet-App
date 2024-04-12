import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { IUser } from 'src/app/models/user.model';
import { FormBuilder, Validators, AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  message = ''
  alertClass = ''

  constructor(private fb: FormBuilder, private authService: AuthService){}

  registerForm = this.fb.group({
    firstName: [null, [Validators.required, Validators.minLength(2)]],
    lastName: [null, [Validators.required, Validators.minLength(2)]],
    loginId: [null, [Validators.required, Validators.minLength(2)]],
    email: [null, [Validators.required, Validators.email]],
    contactNumber: [null, [Validators.required, Validators.minLength(9)]],
    password: [null, [Validators.required, Validators.minLength(6)]],
    confirmPassword: [null, [Validators.required, Validators.minLength(6)]]
  },{ validators: this.passwordMatchValidator});

  get firstName() {return this.registerForm.get('firstName')}
  get lastName() {return this.registerForm.get('lastName')}
  get loginId() {return this.registerForm.get('loginId')}
  get email() {return this.registerForm.get('email')}
  get contactNumber() {return this.registerForm.get('contactNumber')}
  get password() {return this.registerForm.get('password')}
  get confirmPassword() {return this.registerForm.get('confirmPassword')}

  passwordMatchValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const password = control.get('password')?.value;
    const confirmPassword = control.get('confirmPassword')?.value;

    if (password !== confirmPassword) {
      control.get('confirmPassword')?.setErrors({ 'passwordMismatch': true });
      return { 'passwordMismatch': true };
    } else {
      return null;
    }
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const newUser: IUser = {
        firstName: this.registerForm.value.firstName!,
        lastName: this.registerForm.value.lastName!,
        loginId: this.registerForm.value.loginId!,
        email: this.registerForm.value.email!,
        contactNumber: this.registerForm.value.contactNumber!,
        password: this.registerForm.value.password!,
        roles: [],
        tweets: []
      }
      this.authService.register(newUser).subscribe({
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
    }
  }

}
