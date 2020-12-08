import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../api/auth.service';
import { TokenService } from '../api/token.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private auth : AuthService, private token: TokenService) { }

    registerConnection = new FormGroup({
      email: new FormControl(''),
      username: new FormControl(''),
      password1: new FormControl(''),
      password2: new FormControl(''),
      gender: new FormControl('', Validators.required)
    });


  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  btn_register() {
    console.log(this.registerConnection.value.email);
    this.auth.register({login: this.registerConnection.value.username, email: this.registerConnection.value.email, password: this.registerConnection.value.password1}).toPromise()
  }
}
