package fr.brangers.dashboard.service.service.widget.widgetType.weather;

import fr.brangers.dashboard.service.service.widget.widgetType.WidgetType;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class WeatherTemperature extends WidgetType {
    JSONObject weatherInformations = new JSONObject();

    public WeatherTemperature(int id, String type, String nameType, String options) {
        super(id, type, nameType);
        findWeatherWithOptions(options);
    }

    private void findWeatherWithOptions(String options) {
        try {
            JSONObject optionsJson = new JSONObject(options);
            String city = optionsJson.getString("city");

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://api.weatherapi.com/v1/current.json?key=b8ea2cb24b234d608e3171510200712&q=" + city)
                    .queryParam("format", "json");

            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<String> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class);
            JSONObject location = new JSONObject(response.getBody()).getJSONObject("location");
            JSONObject current = new JSONObject(response.getBody()).getJSONObject("current");
            weatherInformations.put("city", location.get("name"));
            weatherInformations.put("region", location.get("region"));
            weatherInformations.put("country", location.get("country"));
            weatherInformations.put("temp", current.get("temp_c"));
            weatherInformations.put("condition", current.getJSONObject("condition").toMap());
            this.data = weatherInformations.toMap();
        } catch (Exception e) {
            weatherInformations.put("city", "Inconnu");
            weatherInformations.put("region", "Inconnu");
            weatherInformations.put("country", "Inconnu");
            weatherInformations.put("temp", "Inconnu");
            weatherInformations.put("condition", "Inconnu");
            this.data = weatherInformations.toMap();
        }
    }
}
