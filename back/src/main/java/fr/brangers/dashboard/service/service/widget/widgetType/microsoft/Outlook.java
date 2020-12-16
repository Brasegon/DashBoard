package fr.brangers.dashboard.service.service.widget.widgetType.microsoft;

import fr.brangers.dashboard.service.service.widget.widgetType.WidgetType;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Outlook extends WidgetType {

    JSONObject outlookInformations = new JSONObject();

    public Outlook(int id, String type, String nameType, String options, int refreshTime) {
        super(id, type, nameType, refreshTime, options);
        findOutlookAuth(options);
    }

    private void findOutlookAuth(String options) {
        try {
            JSONObject optionsJson = new JSONObject(options);
            String auth = optionsJson.getString("auth");

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            headers.set("Authorization", "Bearer " + auth);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://graph.microsoft.com/v1.0/me/mailfolders/inbox/messages?$select=subject,from,receivedDateTime&$top=3&$orderby=receivedDateTime DESC")
                    .queryParam("format", "json");

            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<String> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class);
            JSONObject inf = new JSONObject(response.getBody());
            this.data = inf.toMap();
        } catch (Exception e) {
            System.out.println("no");
            e.printStackTrace();
        }
    }
}
