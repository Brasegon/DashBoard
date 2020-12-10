package fr.brangers.dashboard.service.service.widget;

import fr.brangers.dashboard.controller.service.widget.SWidget;
import fr.brangers.dashboard.message.DataResponse;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.message.Response;
import fr.brangers.dashboard.service.Service;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddWidgetService extends Service {
    private final JSONObject json;
    private final SWidget widget;

    public AddWidgetService(JSONObject json, SWidget widget) {
        this.json = json;
        this.widget = widget;
    }

    @Override
    public IResponse launch() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO widget(type, widget_type, options, user_id) VALUES(?, ?, ?, ?)");
            preparedStatement.setString(1, widget.getType());
            preparedStatement.setString(2, widget.getWidget());
            preparedStatement.setString(3, widget.getOptions());
            preparedStatement.setInt(4, json.getInt("id"));
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return(new Response("Widget added", "success"));
    }

    public JSONObject getJson() {
        return json;
    }

    public SWidget getWidget() {
        return widget;
    }
}
