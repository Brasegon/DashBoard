package fr.brangers.dashboard.service.service.widget;

import fr.brangers.dashboard.controller.service.widget.SWidget;
import fr.brangers.dashboard.controller.service.widget.SWidgetUpdate;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.message.Response;
import fr.brangers.dashboard.service.Service;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateWidgetService extends Service {
    private final JSONObject json;
    private final SWidgetUpdate widget;

    public UpdateWidgetService(JSONObject json, SWidgetUpdate widget) {
        this.json = json;
        this.widget = widget;
    }

    @Override
    public IResponse launch() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE widget SET type = ?, widget_type = ?, options = ?, refreshTime = ? WHERE user_id = ? AND id = ?");
            preparedStatement.setString(1, widget.getType());
            preparedStatement.setString(2, widget.getWidget());
            preparedStatement.setString(3, widget.getOptions());
            preparedStatement.setInt(4, widget.getRefreshTime());
            preparedStatement.setInt(5, json.getInt("id"));
            preparedStatement.setInt(6, widget.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return(new Response("Widget added", "success"));
    }

    public JSONObject getJson() {
        return json;
    }

    public SWidgetUpdate getWidget() {
        return widget;
    }
}
