package fr.brangers.dashboard.service.service.widget.widgetType.minecraft;

import fr.brangers.dashboard.service.service.widget.widgetType.WidgetType;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class MinecraftWidget extends WidgetType {
    private JSONObject minecraftInformations = new JSONObject();

    public MinecraftWidget(int id, String type, String nameType, int refreshTime, String options) {
        super(id, type, nameType, refreshTime, options);
        findMinecraftServer(options);
    }

    private void findMinecraftServer(String options) {
        try {
            JSONObject optionsJson = new JSONObject(options);
            String server = optionsJson.getString("server");

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.mcsrvstat.us/2/" + server)
                    .queryParam("format", "json");

            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<String> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class);
            JSONObject inf = new JSONObject(response.getBody());
            minecraftInformations.put("error", false);
            this.data = inf.toMap();
        } catch (Exception e) {
            minecraftInformations.put("error", true);
            this.data = minecraftInformations.toMap();
        }
    }
}
