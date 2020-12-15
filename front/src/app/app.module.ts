import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { authInterceptorProviders } from './api/request/helper.service';
import { SocialLoginModule, SocialAuthServiceConfig } from 'angularx-social-login';
import {
  GoogleLoginProvider,
  FacebookLoginProvider
} from 'angularx-social-login';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddWidgetComponent } from './widget/add-widget/add-widget.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import { AsteroidComponent } from './asteroid/asteroid.component';
import { WeatherComponent } from './weather/weather.component';
import { DelWidgetComponent } from './widget/del-widget/del-widget.component';
import { EpitechProfilComponent } from './epitech-profil/epitech-profil.component';
import {MatSliderModule} from '@angular/material/slider';
import { MsalModule } from '@azure/msal-angular';
import { OutlookComponent } from './outlook/outlook.component';
@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    AddWidgetComponent,
    AsteroidComponent,
    WeatherComponent,
    DelWidgetComponent,
    EpitechProfilComponent,
    OutlookComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    SocialLoginModule,
    BrowserAnimationsModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatSelectModule,
    MatInputModule,
    MatSliderModule,
    MsalModule.forRoot({
      auth: {
          clientId: "0861f80f-da9a-45b7-9541-d7891117bf61",
          authority: "https://login.microsoftonline.com/common/",
          redirectUri: "http://localhost:4200"
      }
  }, {
      protectedResourceMap: [
          ['https://graph.microsoft.com/v1.0/me', ['user.read', 'Mail.Read']]
      ],
      popUp: true,
  })
  ],
  providers: [{
    provide: 'SocialAuthServiceConfig',
    useValue: {
      autoLogin: false,
      providers: [
        {
          id: GoogleLoginProvider.PROVIDER_ID,
          provider: new GoogleLoginProvider(
            '223631566065-lpkfkp9s1ektcbppr0ck20j9orj94116.apps.googleusercontent.com'
          )
        }
      ]
    } as SocialAuthServiceConfig,
  }, authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
