import { Component, Input, OnInit } from '@angular/core';
import { DelWidgetComponent } from '../widget/del-widget/del-widget.component';
import * as moment from 'moment';
import { HomeComponent } from '../home/home.component';
import { MatDialog } from '@angular/material/dialog';
import { UpdateWidgetComponent } from '../widget/update-widget/update-widget.component';



@Component({
  selector: 'app-outlook',
  templateUrl: './outlook.component.html',
  styleUrls: ['./outlook.component.css']
})
export class OutlookComponent implements OnInit {
  @Input() widget: any;
  constructor(private dialog: MatDialog, private home: HomeComponent) { }

  time = 3;

  ngOnInit(): void {

  }
  getTime(i) {
    return moment(i.receivedDateTime).format('HH:mm - DD/MM');

  }

  openModal(widget: any) {
    let dialogRef = this.dialog.open(DelWidgetComponent, {data: widget, panelClass: 'mybody'});
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.home.getWidget();
    })
  }

  update(widget: any) {
    let dialogRef = this.dialog.open(UpdateWidgetComponent, {data: widget, panelClass: 'mybody'});
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.home.getWidget();
    })
  }
  

}
