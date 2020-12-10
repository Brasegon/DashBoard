import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../api/auth.service';
import { TokenService } from '../api/token.service';
import { SocialAuthService } from "angularx-social-login";
import { FacebookLoginProvider, GoogleLoginProvider } from "angularx-social-login";
import { SocialUser } from "angularx-social-login";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: SocialUser | undefined;
  loggedIn: boolean | undefined;

  constructor(private auth : AuthService, private token: TokenService, private authService: SocialAuthService) { }


    registerConnection = new FormGroup({
      email: new FormControl(''),
      username: new FormControl(''),
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
        this.registerGoogle(user);
      }
    });
  }

  async registerGoogle(user: any) {
      var result = await this.auth.registerGoogle({email: user.email, Oauth: "GOOGLE"}).toPromise();
      console.log(result);
      if (result.status === "error") {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Username or Email already exits',
          confirmButtonColor : '#3DB1C8',
          confirmButtonText: 'OK !'
        });
        this.authService.signOut(true);
      }
      else {
        this.success_message();
      }
  }

  // tslint:disable-next-line:typedef
  IsEmailValid(email: any) {
    return /\S+@\S+\.\S+/.test(email);
  }

  async registerLaunch() {
    var result = await this.auth.register({login: this.registerConnection.value.username, email: this.registerConnection.value.email, password: this.registerConnection.value.password1}).toPromise()
    if (result.status === "error") {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Username or Email already exits',
        confirmButtonColor : '#3DB1C8',
        confirmButtonText: 'OK !'
      });
    }
    else {
      this.success_message();
    }
  }
  // tslint:disable-next-line:typedef
    btn_register(): any{
      const login = this.registerConnection.value.username;
      const email = this.registerConnection.value.email;
      const password = this.registerConnection.value.password1;

      if (this.IsEmailValid(email) !== true) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Your email is not valid...',
          confirmButtonColor : '#3DB1C8',
          confirmButtonText: 'OK !'
        });
        return false;
      }
      if (this.IsUsernameValid(login) !== true) {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Username must have 5-15 characters and be composed only of numbers and alphabets',
            confirmButtonColor : '#3DB1C8',
            confirmButtonText: 'OK !'
          });
          return false;
      }

      if (password.length < 8) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Your password must have at least 8 characters',
          confirmButtonColor : '#3DB1C8',
          confirmButtonText: 'OK !'
        });
        return false;
      }
     this.registerLaunch();

    }

  signInWithGoogle(): void {
    let isCalled = false;
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }

  // tslint:disable-next-line:typedef
  IsUsernameValid(str: any) {
    const illegalChars = /\W/;

    if (str === '') {
      return false;
    } else if ((str.length < 5) || (str.length > 15)) {
        return false;
    } else if (illegalChars.test(str)) {
        return false;
    }
    return true;
  }

  success_message() {
    Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: 'You have been registered',
      showConfirmButton: false,
      timer: 1500
    })
    setTimeout(function() {
     window.location.href = "/login";
  }, 1500);
  }

}
