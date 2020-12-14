import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { AuthService } from '../api/auth.service';
import {MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AddWidgetComponent } from '../widget/add-widget/add-widget.component';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(private auth : AuthService, public dialog: MatDialog, private home: HomeComponent) { }

  ngOnInit(): void {
    if (localStorage.getItem('token') && localStorage.getItem("type")) {
      this.checAuth();
    }
  }

  async checAuth() {
    var token = localStorage.getItem('token');
    var method = localStorage.getItem('type');

    try {
      var result = await this.auth.auth({token: token, method: method}).toPromise();
    } catch (e) {
      localStorage.removeItem('email');
        localStorage.removeItem('token');
        localStorage.removeItem('type');
        Swal.fire({
          position: 'top-end',
          icon: 'error',
          title: 'Your session has expired',
          showConfirmButton: false,
          timer: 1500
        })
        setTimeout(function() {
         window.location.href = "/";
      }, 1500);
    }
  }


  isConnected() {
    if (localStorage.getItem('token')) {
      return true;
    }
    else {
      return false;
    }
  }

  getEmail() {
    return localStorage.getItem('email');
  }

  openAddwidget() {
    let dialogRef = this.dialog.open(AddWidgetComponent, {panelClass: 'mybody'});
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.home.getWidget();
    })
  }

  disconnect()
  {
    Swal.fire({
      title: 'Are you sure?',
      text: "You will be disconnected",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3DB1C8',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes'

    }).then((result) => {
      if (result.isConfirmed) {
        localStorage.removeItem('email');
        localStorage.removeItem('token');
        localStorage.removeItem('type');
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'You have been disconnected',
          showConfirmButton: false,
          timer: 1500
        })
        setTimeout(function() {
         window.location.href = "/";
      }, 1500);

      }
    });
  }
}
