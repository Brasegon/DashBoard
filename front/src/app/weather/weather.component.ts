import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import * as moment from 'moment';
import { AuthService } from '../api/auth.service';
import { HomeComponent } from '../home/home.component';
import { DelWidgetComponent } from '../widget/del-widget/del-widget.component';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {
  @Input() widget: any;
  date: any = moment().format("DD MMM");

  constructor(private auth: AuthService, private dialog: MatDialog, private home: HomeComponent) { }

  ngOnInit(): void {
    console.log(this.widget);
  }


  openModal(widget: any) {
    let dialogRef = this.dialog.open(DelWidgetComponent, {data: widget, panelClass: 'mybody'});
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.home.getWidget();
    })
  }

}
