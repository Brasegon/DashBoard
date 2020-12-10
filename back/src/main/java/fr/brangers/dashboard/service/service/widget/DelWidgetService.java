package fr.brangers.dashboard.service.service.widget;

import fr.brangers.dashboard.controller.service.widget.SWidget;
import fr.brangers.dashboard.controller.service.widget.SWidgetDel;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.message.Response;
import fr.brangers.dashboard.service.Service;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DelWidgetService extends Service {
    private final JSONObject json;
    private final SWidgetDel widget;

    public DelWidgetService(JSONObject json, SWidgetDel widget) {
        this.json = json;
        this.widget = widget;
    }

    @Override
    public IResponse launch() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM widget WHERE id = ? AND user_id = ?");
            preparedStatement.setInt(1, widget.getId());
            preparedStatement.setInt(2, json.getInt("id"));
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return(new Response("Widget #" + widget.getId() + " deleted", "success"));
    }

    public JSONObject getJson() {
        return json;
    }

    public SWidgetDel getWidget() {
        return widget;
    }
}
