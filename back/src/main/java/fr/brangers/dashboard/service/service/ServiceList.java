package fr.brangers.dashboard.service.service;

import fr.brangers.dashboard.message.DataResponse;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.message.Response;
import fr.brangers.dashboard.service.Service;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ServiceList extends Service {
    private JSONObject object;
    private ArrayList<String> arrayList = new ArrayList<>();
    public ServiceList(JSONObject object) {
        this.object = object;
        arrayList.add("weather");
    }

    @Override
    public IResponse launch() {
        ArrayList<String> serviceList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT dashboard from users WHERE email = ?");
            preparedStatement.setString(1, object.getString("email"));
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            String jsonString = rs.getString("dashboard");
            if (jsonString == null) {
                return new DataResponse("OK", "success", arrayList);
            }
            JSONObject json = new JSONObject(jsonString);
            for (String mot : arrayList) {
                if (!json.has(mot)) {
                    serviceList.add(mot);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new DataResponse("OK", "success", serviceList);
    }

    public JSONObject getObject() {
        return object;
    }
}
