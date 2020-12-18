
# T-JAV-501-MAR-5-1-dashboard-brandon.segers
[![Build Status](https://travis-ci.com/Brasegon/DashBoard.svg?token=9x6YSMXsF91xTCYR2pby&branch=main)](https://travis-ci.com/Brasegon/DashBoard)

# Team
* Valentin Lyon (valentin.lyon@epitech.eu) ---> Front End
* Brandon Segers (brandon.segers@epitech.eu) ---> Back End

## Getting Started

### Prerequisites
* JAVA Jdk 15
* NodeJS

## List of availaible widget :
- [x] __Weather Service__
  - [x] Weather Widget
- [x] __Epitech Service__
  - [x] Intra Epitech Widget
- [x] __Minecraft Service__
  - [x] Minecraft Server Widget
- [x] __Microsoft Service__
  - [x] Outlook Widget
- [x] __Crypto Money Service__
  - [x] Crypto Price Widget


# API

## Installation
Build with Gradle <br>
* Windows Build
 ```sh
  .\gradlew build
  ```
  * Go into /build/libs and copy DashBoard-1.0-SNAPSHOT.jar
   ```sh
  cd .\build\libs && cp DashBoard-1.0-SNAPSHOT.jar
  ```
  * Linux Build
 ```sh
  ./gradlew build
  ```
  * Go into /build/libs and copy DashBoard-1.0-SNAPSHOT.jar
   ```sh
  cd ./build/libs && cp DashBoard-1.0-SNAPSHOT.jar
  ```
## Run
```sh
java -jar DashBoard-1.0-SNAPSHOT.jar
  ```

## Developp
* Open Intellij Idea and import gradle project
* Run ServerMain.java into fr.brangers for launch the server

### Create a new API widget
* Go to "fr.brangers.dashboard.service.service.widget"
* Create a new class in widgetType who extends WidgetType

```java
public class ExempleWidget extends WidgetType {

    JSONObject widgetInformations = new JSONObject();

    public ExempleWidget(int id, String type, String nameType, String options, int refreshTime) {
        super(id, type, nameType, refreshTime, options);
        findWidgetInformations(options);
        this.data = widgetInformations.toMap() /* Put the response in data */
    }
}
  ```
* Next, go in "fr.brangers.dashboard.service.service.widget"
* In GetWidgets.java, add the following lines :
```java
case "exemple NameType Widget":
    array.put(new ExampleWidget(rs.getInt("id"), rs.getString("type"), rs.getString("widget_type"), rs.getInt("refreshTime"), rs.getString("options")));
    break;
 ```
* In GetWidget.java, add the following lines :
```java
case "exemple NameType Widget":
    widget = new ExampleWidget(rs.getInt("id"), rs.getString("type"), rs.getString("widget_type"), rs.getInt("refreshTime"), rs.getString("options"));
    break;
 ```
 ### Structure of API
 * Main Class ---> fr.brangers
 * Utils Class (ex. JWToken) ---> fr.brangers.utils
 * Dashboard Class ---> fr.brangers.dashboard
	 * Controller ---> fr.brangers.dashboard.controller (Contains all of the api request)
	 * Message ---> fr.brangers.dashboard.message (Contains custom message for request)
	 * Service ---> fr.brangers.dashboard.service (Contains all widgets execution and login / register execution)

# Front
## Installation
* Install dependencies
```sh
npm install && npm install -g @angular/cli
  ```
## Run
* Run the server with Angular CLI
```sh
ng serve -o
  ```
* Run the server with npm
```sh
npm start
  ```
## Developp
* Open Visual Studio Code or any Web IDE and open a project folder.
### Create a new Front widget
* Create a new component
```sh
ng g c widget/name_of_widget
 ```
 * Add the following code in your component
```typescript 
import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { HomeComponent } from '../home/home.component';
import { DelWidgetComponent } from '../widget/del-widget/del-widget.component';
import { UpdateWidgetComponent } from '../widget/update-widget/update-widget.component';

@Component({
  selector: 'app-example',
  templateUrl: './example.component.html',
  styleUrls: ['./example.component.css']
})
export class ExampleComponents implements OnInit {
  @Input() widget: any;
  constructor(private dialog: MatDialog, private home: HomeComponent) { }

  ngOnInit(): void {
  }

  openModal(widget: any) {
    let dialogRef = this.dialog.open(DelWidgetComponent, {data: widget, panelClass: 'mybody'});
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.home.getWidget();
    })
  }

  update(widget: any) {
    let dialogRef = this.dialog.open(UpdateWidgetComponent, {data: widget, panelClass: 'mybody'});
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.home.getWidget();
    })
  }
}
```
* Add your html code in example-component.html
```html
<div (click)="openModal(widget)" class="close-container" >
    <div class="leftright"></div> <!-- open delete component-->
    <div class="rightleft"></div>
  </div>
  <ul class="liste">
    <li><a (click)="update(widget)"><i class="fa fa-cog" aria-hidden="true"></i></a></li> <!-- open update component-->
  </ul>
  ```
  * Let's go to the home.component.html and add the following code in the DIV
```html
<app-example *ngIf="widget.nameType === 'exampleNameType'" [widget]='widget'></app-exemple>
  ```
  * Go in add-widget and update-widget html and add the following code
```html
<div *ngIf="widget.type === 'ExampleType'" >
	<!-- Add your differents options (Input, Select etc..) -->
</div>
  ```
  * In add-widget and update-widget typescript, add the following code in Widget_Group
```typescript
{
  name: "Example Service",
  widget: [
    {value: "Example Widget"}
  ]
}
  ```
   * In the function addWidget
```typescript
if (this.widget.type === "Example Type") {
      this.widget.widget = "Example Name Type";
      var options: any = {
        // Your options of widgets
      }
      this.widget.refreshTime = this.refreshTime;
      this.widget.options = JSON.stringify(options);
      var result = await this.auth.addWidget(this.widget).toPromise();
      this.dialogRef.close(result);
}
  ```

<h1> Your widget is ready to use :)</h1>
