import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
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
