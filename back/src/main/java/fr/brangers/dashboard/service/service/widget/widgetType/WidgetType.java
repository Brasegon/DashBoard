package fr.brangers.dashboard.service.service.widget.widgetType;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class WidgetType {
    protected String type;
    protected String nameType;
    protected int id;
    protected Map<String, Object> data;

    protected WidgetType(int id, String type, String nameType) {
        this.type = type;
        this.id = id;
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

    public int getId() {
        return id;
    }
}
