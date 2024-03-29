import { Component, OnInit } from '@angular/core';
import { AuthService } from '../api/auth.service';
import {MatDialog, MatDialogConfig } from '@angular/material/dialog';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  widgetList: any;

  constructor(private auth: AuthService, private dialog: MatDialog) { }
  ngOnInit(): void {
    if (this.isConnected()) {
      this.getWidget();
    }
  }

  isConnected() {
    if (localStorage.getItem("token")) {
      return true;
    }
    else {
      return false;
    }
  }

  async getWidget() {
      var result = await this.auth.getWidget().toPromise();

      this.widgetList = result.data;
      console.log(this.widgetList);
  }

}
