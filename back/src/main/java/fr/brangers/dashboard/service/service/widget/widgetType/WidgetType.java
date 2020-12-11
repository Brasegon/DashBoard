package fr.brangers.dashboard.service.service.widget.widgetType;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class WidgetType {
    protected String type;
    protected String nameType;
    protected Map<String, Object> data;

    protected WidgetType(String type, String nameType) {
        this.type = type;
        this.nameType = nameType;
        this.data = new HashMap<>();
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
}
