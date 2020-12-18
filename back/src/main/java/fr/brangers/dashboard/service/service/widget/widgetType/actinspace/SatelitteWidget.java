package fr.brangers.dashboard.service.service.widget.widgetType.actinspace;

import fr.brangers.dashboard.service.service.widget.widgetType.WidgetType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class SatelitteWidget extends WidgetType {
    private JSONObject satInfo = new JSONObject();
    public SatelitteWidget(int id, String type, String nameType, int refreshTime, String options) {
        super(id, type, nameType, refreshTime, options);
        findSatelitte(options);
    }

    private void findSatelitte(String options) {
        try {
            JSONObject optionsJson = new JSONObject(options);
            String id = optionsJson.getString("id");

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://brangers.eu:3000/getSatInfo?id=" + id)
                    .queryParam("format", "json");

            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<String> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class);
            JSONObject data = new JSONObject(response.getBody());
            this.data = data.toMap();
        } catch (Exception e) {
            satInfo.put("error", true);
            this.data = satInfo.toMap();
        }
    }
}
