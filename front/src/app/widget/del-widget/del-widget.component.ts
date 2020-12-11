import { Component, OnInit, Inject } from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import { AuthService } from 'src/app/api/auth.service';

@Component({
  selector: 'app-del-widget',
  templateUrl: './del-widget.component.html',
  styleUrls: ['./del-widget.component.css']
})
export class DelWidgetComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private auth: AuthService) { }

  ngOnInit(): void {

  }

  async removeWidget() {
    let result = await this.auth.removeWidget({id: this.data.id}).toPromise();
  }

}
