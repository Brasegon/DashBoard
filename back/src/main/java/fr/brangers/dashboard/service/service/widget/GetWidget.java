package fr.brangers.dashboard.service.service.widget;

import fr.brangers.dashboard.message.DataResponse;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.service.Service;
import fr.brangers.dashboard.service.service.widget.widgetType.WidgetType;
import fr.brangers.dashboard.service.service.widget.widgetType.epitech.Epitech;
import fr.brangers.dashboard.service.service.widget.widgetType.microsoft.Outlook;
import fr.brangers.dashboard.service.service.widget.widgetType.minecraft.MinecraftWidget;
import fr.brangers.dashboard.service.service.widget.widgetType.weather.WeatherTemperature;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetWidget extends Service {

    private final JSONObject token;
    private WidgetType widget;
    private int id;

    public GetWidget(JSONObject token, int id) {
        this.token = token;
        this.id = id;
    }

    @Override
    public IResponse launch() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from widget WHERE user_id = ? AND id = ?");
            preparedStatement.setInt(1, token.getInt("id"));
            preparedStatement.setInt(2, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                getWidgetInformation(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new DataResponse("List of widget", "success", widget);
    }

    private void getWidgetInformation(ResultSet rs) throws SQLException {
        switch (rs.getString("widget_type")) {
            case "weather_temperature":
                widget = new WeatherTemperature(rs.getInt("id"), "Weather", "température", rs.getString("options"), rs.getInt("refreshTime"));
                break;
            case "epitech_user":
                widget = new Epitech(rs.getInt("id"), "EpitechProfil", "epitech_user", rs.getString("options"), rs.getInt("refreshTime"));
            case "outlook":
                widget = new Outlook(rs.getInt("id"), "Outlook", "outlook", rs.getString("options"), rs.getInt("refreshTime"));
                break;
            case "minecraft_server":
                widget = new MinecraftWidget(rs.getInt("id"), "Minecraft", "minecraft_server", rs.getInt("refreshTime"), rs.getString("options"));
        }
    }
}
