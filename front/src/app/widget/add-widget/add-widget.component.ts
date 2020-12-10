import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from 'src/app/api/auth.service';
@Component({
  selector: 'app-add-widget',
  templateUrl: './add-widget.component.html',
  styleUrls: ['./add-widget.component.css']
})
export class AddWidgetComponent implements OnInit {

  constructor(private auth : AuthService) { }
  widgetSelected = 'Weather'
  widget = {
    type : "",
    widget: "",
    options: "",
  }
  weather = {
    city: new FormControl('')
  }
  
  widgets =  [
    {value: 'Weather'},
    {value: 'test'}
  ];

  ngOnInit(): void {
  
  }
  close() {

  }

  addWidget() {
    if (this.widget.type === "Weather") {
      var options = {
        city: this.weather.city.value
      }
      this.widget.options = JSON.stringify(options);
      this.auth.addWidget(this.widget).toPromise();
    }
  }
}
