import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginForm ! : FormGroup;
  hidePassword = true;

  constructor(private formBuilder : FormBuilder,
    private authService : AuthService,
    private snackbar : MatSnackBar
  ) {
    this.loginForm = this.formBuilder.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]],
    })
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(){
    console.log(this.loginForm.value);
    this.authService.login(this.loginForm.value).subscribe((res)=>{
      console.log(res);
      if(res.id != null){
        this.snackbar.open('Login successful', 'Close', {duration:5000});
      }else{
        this.snackbar.open('Login failed, Try again', 'Close', {duration:5000, panelClass: 'error-snackbar'});
      }
    })
  }

}
