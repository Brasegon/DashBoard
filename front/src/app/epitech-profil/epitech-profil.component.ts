import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-epitech-profil',
  templateUrl: './epitech-profil.component.html',
  styleUrls: ['./epitech-profil.component.css']
})
export class EpitechProfilComponent implements OnInit {
  @Input() widget: any;
  constructor() { }

  ngOnInit(): void {
  }

}
