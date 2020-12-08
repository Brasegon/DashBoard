import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../api/auth.service';
import { TokenService } from '../api/token.service';
import { SocialAuthService } from "angularx-social-login";
import { FacebookLoginProvider, GoogleLoginProvider } from "angularx-social-login";
import { SocialUser } from "angularx-social-login";
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
      this.registerGoogle(user);
    });
  }

  async registerGoogle(user : any) {
      var result = await this.auth.registerGoogle({email: user.email, Oauth: "GOOGLE"}).toPromise();
  }

  // tslint:disable-next-line:typedef
  btn_register() {
    this.auth.register({login: this.registerConnection.value.username, email: this.registerConnection.value.email, password: this.registerConnection.value.password1}).toPromise()
  }

  signInWithGoogle(): void {
    let isCalled = false;
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }
}
