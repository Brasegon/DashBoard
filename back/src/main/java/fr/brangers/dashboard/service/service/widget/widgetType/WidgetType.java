package fr.brangers.dashboard.service.service.widget.widgetType;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class WidgetType {
    protected String type;
    protected String nameType;
    protected int id;
    protected int refreshTime;
    protected String options;
    protected Map<String, Object> data;

    protected WidgetType(int id, String type, String nameType, int refreshTime, String options) {
        this.type = type;
        this.id = id;
        this.nameType = nameType;
        this.data = new HashMap<>();
        this.refreshTime = refreshTime;
        this.options = options;
    }

    public int getRefreshTime() {
        return refreshTime;
    }

    public String getType() {
        return type;
    }

    public String getNameType() {
        return nameType;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public String getOptions() {
        return options;
    }
}
