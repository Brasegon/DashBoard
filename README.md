# T-JAV-501-MAR-5-1-dashboard-brandon.segers
[![Build Status](https://travis-ci.com/Brasegon/DashBoard.svg?token=9x6YSMXsF91xTCYR2pby&branch=main)](https://travis-ci.com/Brasegon/DashBoard)

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
