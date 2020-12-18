import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { HomeComponent } from '../home/home.component';
import { DelWidgetComponent } from '../widget/del-widget/del-widget.component';
import { UpdateWidgetComponent } from '../widget/update-widget/update-widget.component';

@Component({
  selector: 'app-asteroid',
  templateUrl: './asteroid.component.html',
  styleUrls: ['./asteroid.component.css']
})
export class AsteroidComponent implements OnInit {
  @Input() widget: any;
  constructor(private dialog: MatDialog, private home: HomeComponent) { }
  markers: any = [];
  center = {};
  ngOnInit(): void {
      this.center = {
        lat: this.widget.data.info.lat,
        lng: this.widget.data.info.lng,
      }

      this.markers = [{
        position: {
          lat: this.widget.data.info.lat,
          lng: this.widget.data.info.lng,
        },
        label: {
          color: 'red',
          text: this.widget.data.name,
        },
        options: { animation: google.maps.Animation.BOUNCE },
      }];
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
