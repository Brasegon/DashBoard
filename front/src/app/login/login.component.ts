import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../api/auth.service';
import { TokenService } from '../api/token.service';
import { SocialAuthService } from "angularx-social-login";
import { FacebookLoginProvider, GoogleLoginProvider } from "angularx-social-login";
import { SocialUser } from "angularx-social-login";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: SocialUser | undefined;
  loggedIn: boolean | undefined;

  constructor(private auth: AuthService, private token: TokenService, private authService: SocialAuthService) { }

  loginConnection = new FormGroup({
    email: new FormControl(''),
    password1: new FormControl(''),
  });


  ngOnInit(): void {
    let isCalled = false;
    //this.authService.authState.subscribe(user.access)
    this.authService.authState.subscribe((user) => {
      this.user = user;
      console.log(user);
      this.loggedIn = (user != null);
      if (this.loggedIn) {
        this.loginGoogle(user);
      }
    });
  }

  // tslint:disable-next-line:typedef
  btn_login() {
    this.login_launch();
  }

  async login_launch()
  {

    var result = await this.auth.login({email: this.loginConnection.value.email, password : this.loginConnection.value.password1}).toPromise();
    if (result.status === "error") {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Incorrect email or password',
        confirmButtonColor : '#3DB1C8',
        confirmButtonText: 'OK !'
      });
    }
    else {
      this.success_message();
      localStorage.setItem('type', 'legacy');
      localStorage.setItem('token', result.data.token);
      localStorage.setItem('email', result.data.email);
    }
  }

  loginInWithGoogle(): any
  {
    let isCalled = false;
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }

  async loginGoogle(user: any) {
    var result = await this.auth.loginGoogle({email: user.email, token: user.idToken}).toPromise();
    console.log(result);
    if (result.status === "error") {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Email does not exist',
        confirmButtonColor : '#3DB1C8',
        confirmButtonText: 'OK !'
      });
    }
    else {
      this.success_message();
      localStorage.setItem('type', 'google');
      localStorage.setItem('token', user.idToken);
      localStorage.setItem('email', user.email);

    }
}

success_message() {
  Swal.fire({
    position: 'top-end',
    icon: 'success',
    title: 'You are connected',
    showConfirmButton: false,
    timer: 1500
  })
  setTimeout(function() {
   window.location.href = "/";
}, 1500);
}
}
