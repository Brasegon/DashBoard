import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from 'src/app/api/auth.service';
@Component({
  selector: 'app-add-widget',
  templateUrl: './add-widget.component.html',
  styleUrls: ['./add-widget.component.css']
})
export class AddWidgetComponent implements OnInit {

  constructor(private auth : AuthService, public dialogRef: MatDialogRef<AddWidgetComponent>) { }
  widgetSelected = 'Weather'
  widget = {
    type : "",
    widget: "",
    options: "",
  }
  weather = {
    city: new FormControl('')
  }

  epitech_user = new FormControl('');

  widgets =  [
    {value: 'Weather'},
    {value: 'EpitechProfil'}
  ];

  ngOnInit(): void {

  }

  async addWidget() {
    if (this.widget.type === "Weather") {
      this.widget.widget = "weather_temperature";
      var options: any = {
        city: this.weather.city.value
      }
      this.widget.options = JSON.stringify(options);
      var result = await this.auth.addWidget(this.widget).toPromise();
      this.dialogRef.close(result);
    }

    if (this.widget.type === "EpitechProfil") {
      this.widget.widget = "epitech_user";
      var options: any = {
        auth: this.epitech_user.value
      }
      this.widget.options = JSON.stringify(options);
      var result = await this.auth.addWidget(this.widget).toPromise();
      this.dialogRef.close(result);
    }
  }
}
