import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  signupForm! : FormGroup;
  hidePassword = true;

  constructor(private formBuilder : FormBuilder,
    private snackBar : MatSnackBar,
    private authService: AuthService,
    private router : Router
  ) {
    this.signupForm = this.formBuilder.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]],
      confirmPassword: [null, [Validators.required]],
    })
  }

  togglePasswordVisibility(){
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(){
    console.log(this.signupForm.value);
    const password = this.signupForm.get('password')?.value;
    const confirmPassword = this.signupForm.get('confirmPassword')?.value;
    if(password!==confirmPassword){
      this.snackBar.open('Password do not match','Close',{duration:5000, panelClass:'error-snackbar'});
      return;
    }
    this.authService.signup(this.signupForm.value).subscribe(res=>{
      console.log(res);
      if(res.id != null){
        this.snackBar.open('Signup Successful','Close', {duration:5000})
        this.router.navigateByUrl("/login");
      }else{
        this.snackBar.open("Signup failed !!! Try again","Close", {duration:5000, panelClass: 'error-snackbar'});
      }
    })
  }
}
