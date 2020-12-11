package fr.brangers.dashboard.service.service.widget;

import fr.brangers.dashboard.message.DataResponse;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.message.Response;
import fr.brangers.dashboard.service.Data;
import fr.brangers.dashboard.service.Service;
import fr.brangers.dashboard.service.service.widget.widgetType.weather.WeatherTemperature;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetWidgets extends Service {

    private final JSONObject token;
    private final JSONArray array = new JSONArray();
    public GetWidgets(JSONObject token) {
        this.token = token;
    }

    @Override
    public IResponse launch() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from widget WHERE user_id = ?");
            preparedStatement.setInt(1, token.getInt("id"));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                getWidgetInformation(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new DataResponse("List of widget", "success", array.toList());
    }

    private void getWidgetInformation(ResultSet rs) throws SQLException {
        switch (rs.getString("widget_type")) {
            case "weather_temperature":
                array.put(new WeatherTemperature("weather", "température", rs.getString("options")));
        }
    }
}
