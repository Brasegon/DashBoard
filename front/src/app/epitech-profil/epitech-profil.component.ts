import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DelWidgetComponent } from '../widget/del-widget/del-widget.component';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-epitech-profil',
  templateUrl: './epitech-profil.component.html',
  styleUrls: ['./epitech-profil.component.css']
})
export class EpitechProfilComponent implements OnInit {
  @Input() widget: any;
  constructor(private dialog: MatDialog,  private home: HomeComponent) { }

  ngOnInit(): void {

  }

  openModal(widget: any) {
    let dialogRef = this.dialog.open(DelWidgetComponent, {data: widget, panelClass: 'mybody'});
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.home.getWidget();
    })
  }


}
