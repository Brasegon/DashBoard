import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }

  loginConnection = new FormGroup({
    email: new FormControl(''),
    password1: new FormControl(''),
  });


  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  btn_login() {

  }
}
