package fr.brangers.dashboard.service.service.widget.widgetType.crypto;

import fr.brangers.dashboard.service.service.widget.widgetType.WidgetType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Cryptowidget extends WidgetType {

    private JSONObject cryptoInformations = new JSONObject();
    public Cryptowidget(int id, String type, String nameType, int refreshTime, String options) {
        super(id, type, nameType, refreshTime, options);
        findCrypto(options);
    }

    private void findCrypto(String options) {
        try {
            JSONObject optionsJson = new JSONObject(options);
            String money = optionsJson.getString("money");

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.nomics.com/v1/currencies/ticker?key=ffb51a144cc57392288a5ec959d95ced&ids=" + money + "&interval=0d&convert=EUR&per-page=100&page=1")
                    .queryParam("format", "json");

            HttpEntity<?> entity = new HttpEntity<>(headers);

            HttpEntity<String> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class);
            System.out.println(response.getBody());
            JSONArray data = new JSONArray(response.getBody());
            JSONObject inf = new JSONObject(data.getJSONObject(0).toMap());
            cryptoInformations.put("error", false);
            this.data = inf.toMap();
        } catch (Exception e) {
            //e.printStackTrace();
            cryptoInformations.put("error", true);
            this.data = cryptoInformations.toMap();
        }
    }
}
