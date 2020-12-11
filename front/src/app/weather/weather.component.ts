import { Component, Input, OnInit } from '@angular/core';
import * as moment from 'moment';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {
  @Input() widget: any;
  date: any = moment().format("DD MMM");

  constructor() { }

  ngOnInit(): void {
    console.log(this.widget);
  }

}
