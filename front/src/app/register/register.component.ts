import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor() {}

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
  }
}
