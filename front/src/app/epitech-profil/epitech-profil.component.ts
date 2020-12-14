import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DelWidgetComponent } from '../widget/del-widget/del-widget.component';
import { HomeComponent } from '../home/home.component';
import { AuthService } from '../api/auth.service';

@Component({
  selector: 'app-epitech-profil',
  templateUrl: './epitech-profil.component.html',
  styleUrls: ['./epitech-profil.component.css']
})
export class EpitechProfilComponent implements OnInit {
  @Input() widget: any;
  constructor(private auth: AuthService, private dialog: MatDialog,  private home: HomeComponent) { }

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


}
