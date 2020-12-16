import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BroadcastService, MsalService } from '@azure/msal-angular';
import { AuthService } from 'src/app/api/auth.service';
import { AddWidgetComponent } from '../add-widget/add-widget.component';

interface Widget {
  value: string;
}

interface WidgetGroup {
  disabled?: boolean;
  name: string;
  widget: Widget[];
}

@Component({
  selector: 'app-update-widget',
  templateUrl: './update-widget.component.html',
  styleUrls: ['./update-widget.component.css']
})
export class UpdateWidgetComponent implements OnInit {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private auth : AuthService, public dialogRef: MatDialogRef<AddWidgetComponent>, private authService: MsalService, private broadcastService: BroadcastService) { }


  widgetSelected = 'Weather'
  refreshTime = this.data.refreshTime;
  widget = {
    type : this.data.type,
    widget: this.data.nameType,
    refreshTime: this.data.refreshTime,
    options: "",
    id: this.data.id
  }
  weather = {
    city: new FormControl('')
  }

  epitech_user = new FormControl('');

  widgetGroup: WidgetGroup[] = [
    {
      name: "Weather Service",
      widget: [
        {value: 'Weather'}
      ]
    }, {
      name: "Epitech Service",
      widget: [
        {value: 'EpitechProfil'}
      ]
    }, {
      name: "Microsoft Service",
      widget: [
        {value: "Outlook"}
      ]
    }
  ]

  widgets =  [
    {value: 'Weather'},
    {value: 'EpitechProfil'}
  ];

  microsoftToken = "";

  ngOnInit(): void {
    console.log(this.data);
    if (this.widget.type === "Weather") {
      var options = JSON.parse(this.data.options);
      this.weather.city = new FormControl(options.city);
    }

    if (this.widget.type === "EpitechProfil") {
      var options = JSON.parse(this.data.options);
      this.epitech_user = new FormControl(options.auth);
    }
    if (this.widget.type === "Outlook") {
      var options = JSON.parse(this.data.options);
      this.microsoftToken = options.auth;
    }
  }

  changeRefreshTime(event) {
    console.log(event.value);
    this.refreshTime = event.value;
  
  }

  async addWidget() {
    if (this.widget.type === "Weather") {
      this.widget.widget = "weather_temperature";
      var options: any = {
        city: this.weather.city.value
      }
      this.widget.refreshTime = this.refreshTime;
      this.widget.options = JSON.stringify(options);
    }

    if (this.widget.type === "EpitechProfil") {
      this.widget.widget = "epitech_user";
      var options: any = {
        auth: this.epitech_user.value
      }
      this.widget.options = JSON.stringify(options);
      this.widget.refreshTime = this.refreshTime;
    }

    if (this.widget.type === "Outlook") {
      this.widget.widget = "outlook";
      var options: any = {
        auth: this.microsoftToken
      }
      this.widget.options = JSON.stringify(options);
      this.widget.refreshTime = this.refreshTime;
    }
    var result = await this.auth.updateWidget(this.widget).toPromise();
    this.dialogRef.close(result);
  }
  connectMicrosoft(): any {
    const requestObj = {
      scopes: ["user.read", "Mail.Read"]
    };
    var vm = this;
    this.authService.loginPopup({
      scopes: ["user.read", "openid", "profile", "Mail.Read"]
    }).then(function () {
      vm.authService.acquireTokenSilent(requestObj).then(function (tokenResponse) {
        console.log(tokenResponse)
        vm.microsoftToken = tokenResponse.accessToken;
        console.log(tokenResponse);
      });
    });
  }

}
