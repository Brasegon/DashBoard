import { Component, OnInit } from '@angular/core';
import { AuthService } from '../api/auth.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  widgetList: any;

  constructor(private auth: AuthService) { }
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
      var row = [];
      var col = []; 
      for (let i = 0; i < result.data.length; i += 1) {
        col.push(result.data[i]);
        if (i != 0 && i % 2 === 0) {
          row.push(col);
          col = [];
        }
      }
      row.push(col);
      this.widgetList = result.data;
      console.log(this.widgetList);
  }
}
