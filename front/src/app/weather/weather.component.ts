import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import * as moment from 'moment';
import { callbackify } from 'util';
import { AuthService } from '../api/auth.service';
import { HomeComponent } from '../home/home.component';
import { DelWidgetComponent } from '../widget/del-widget/del-widget.component';
import { UpdateWidgetComponent } from '../widget/update-widget/update-widget.component';


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
    var vm = this;
    setTimeout(function() {
      vm.callApi(vm);
    }, vm.widget.refreshTime * 60000)
  }

  async callApi(vm) {
    var res = await vm.auth.getWidgetSpecific(vm.widget.id).toPromise();
    vm.widget = res.data;
    setTimeout(function() {
      vm.callApi(vm);
    }, vm.widget.refreshTime * 60000)
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
