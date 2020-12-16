package fr.brangers.dashboard.service.service.widget.widgetType.epitech;

import fr.brangers.dashboard.service.service.widget.widgetType.WidgetType;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Epitech extends WidgetType {

    JSONObject epitechInformations = new JSONObject();

    public Epitech(int id, String type, String nameType, String options, int refreshTime) {
        super(id, type, nameType, refreshTime, options);
        findEpitechInformations(options);
    }

    private void findEpitechInformations(String options) {
        try {
            JSONObject optionsJson = new JSONObject(options);
            String auth = optionsJson.getString("auth");

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://intra.epitech.eu/" + auth + "/user/?format=json")
                    .queryParam("format", "json");

            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<String> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class);
            JSONObject inf = new JSONObject(response.getBody());
            epitechInformations.put("name", inf.get("title"));
            epitechInformations.put("promo", inf.get("promo"));
            epitechInformations.put("credits", inf.get("credits"));
            epitechInformations.put("school_title", inf.get("school_title"));
            epitechInformations.put("gpa", inf.get("gpa"));
            epitechInformations.put("internal_email", inf.get("internal_email"));
            epitechInformations.put("picture", "https://intra.epitech.eu/" + auth + inf.get("picture"));
            epitechInformations.put("error", false);
            this.data = epitechInformations.toMap();
        } catch (Exception e) {
            epitechInformations.put("error", true);
            this.data = epitechInformations.toMap();
        }
    }

}
