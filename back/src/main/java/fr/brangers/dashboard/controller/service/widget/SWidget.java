package fr.brangers.dashboard.controller.service.widget;

public class SWidget {
    private String type;
    private String widget;
    private String options;
    private int refreshTime;

    public int getRefreshTime() {
        return refreshTime;
    }

    public String getType() {
        return type;
    }

    public String getWidget() {
        return widget;
    }

    public String getOptions() {
        return options;
    }
}
