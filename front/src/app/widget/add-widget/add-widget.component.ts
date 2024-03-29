import { Component, Input, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from 'src/app/api/auth.service';
import { BroadcastService, MsalService } from '@azure/msal-angular';


interface Widget {
  value: string;
}

interface WidgetGroup {
  disabled?: boolean;
  name: string;
  widget: Widget[];
}
@Component({
  selector: 'app-add-widget',
  templateUrl: './add-widget.component.html',
  styleUrls: ['./add-widget.component.css']
})
export class AddWidgetComponent implements OnInit {
  constructor(private auth : AuthService, public dialogRef: MatDialogRef<AddWidgetComponent>, private authService: MsalService, private broadcastService: BroadcastService) { }

  widgetSelected = 'Weather'
  refreshTime = 5;
  widget = {
    type : "",
    widget: "",
    refreshTime: 5,
    options: "",
  }
  weather = {
    city: new FormControl('')
  }

  epitech_user = new FormControl('');

  minecraft_server = new FormControl('');

  crypto = new FormControl('');

  satellite_id = new FormControl('');


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
    },
    {
      name: "Minecraft Service",
      widget: [
        {value: "Minecraft"}
      ]
    },
    {
      name: "Crypto Service",
      widget: [
        {value: "Crypto"}
      ]
    },
    {
      name: "Satellite Service",
      widget: [
        {value: "Satellite"}
      ]
    }
  ]

  microsoftToken = "";

  ngOnInit(): void {

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
      var result = await this.auth.addWidget(this.widget).toPromise();
      this.dialogRef.close(result);
    }

    if (this.widget.type === "Minecraft") {
      this.widget.widget = "minecraft_server";
      var options: any = {
        server: this.minecraft_server.value
      }
      this.widget.refreshTime = this.refreshTime;
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
      this.widget.refreshTime = this.refreshTime;
      var result = await this.auth.addWidget(this.widget).toPromise();
      this.dialogRef.close(result);
    }

    if (this.widget.type === "Outlook") {
      this.widget.widget = "outlook";
      var options: any = {
        auth: this.microsoftToken
      }
      this.widget.options = JSON.stringify(options);
      this.widget.refreshTime = this.refreshTime;
      var result = await this.auth.addWidget(this.widget).toPromise();
      this.dialogRef.close(result);
    }

    if (this.widget.type === "Crypto") {
      this.widget.widget = "crypto";
      var options: any = {
        money: this.crypto.value
      }
      this.widget.options = JSON.stringify(options);
      this.widget.refreshTime = this.refreshTime;
      var result = await this.auth.addWidget(this.widget).toPromise();
      this.dialogRef.close(result);
    }

    if (this.widget.type === "Satellite") {
      this.widget.widget = "satellite";
      var options: any = {
        id: this.satellite_id.value
      }
      this.widget.options = JSON.stringify(options);
      this.widget.refreshTime = this.refreshTime;
      var result = await this.auth.addWidget(this.widget).toPromise();
      this.dialogRef.close(result);
    }
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
