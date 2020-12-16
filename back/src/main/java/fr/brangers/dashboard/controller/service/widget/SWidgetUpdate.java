package fr.brangers.dashboard.controller.service.widget;

public class SWidgetUpdate {
    private String type;
    private String widget;
    private String options;
    private int refreshTime;
    private int id;

    public String getType() {
        return type;
    }

    public String getWidget() {
        return widget;
    }

    public String getOptions() {
        return options;
    }

    public int getRefreshTime() {
        return refreshTime;
    }

    public int getId() {
        return id;
    }
}
