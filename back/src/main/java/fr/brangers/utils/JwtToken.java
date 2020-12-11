package fr.brangers.utils;

import fr.brangers.dashboard.controller.login.SLogin;
import fr.brangers.dashboard.controller.register.SerializeRegister;
import fr.brangers.dashboard.service.Service;
import fr.brangers.dashboard.service.login.DataLogin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;

public class JwtToken {

    private static Connection connection;

    public static String createToken(SLogin user, int id) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, "Q3LDqWVyIHVuZSBBUEkgZW4gSmF2YSBlc3QgdW4gc3VqZXQgZMOpasOgIGxhcmdlbWVudCB0cmFpdMOpLiBTaSB2b3VzIHV0aWxpc2V6IEplcnNleSBldCBzZXMgYW5ub3RhdGlvbnMsIHZvdXMgc2F2ZXogcXVlIGPigJllc3QgdHLDqHMgc2ltcGxlIGV0IHJhcGlkZSDDoCBtZXR0cmUgZW4gcGxhY2UuIERhbnMgY2V0IGFydGljbGUsIGplIHZvdWRyYWlzIHNpbXBsZW1lbnQgdm91cyBtb250cmVyIGNlIHF14oCZb24gcGV1dCBmYWlyZSBhdmVjIFZlcnQueCBlbiByZXZlbmFudCBhdXggZm9uZGFtZW50YXV4LiBFdCBlbiB2w6lyaXTDqSwgbGEgY3LDqWF0aW9uIGTigJl1bmUgQVBJIHNlcmEgYXZhbnQgdG91dCB1biBwcsOpdGV4dGUu")
                .setClaims(buildUserClaims(user, id))
                .setIssuedAt(new Date())
                .compact();
    }

    public static boolean verifyToken(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("Q3LDqWVyIHVuZSBBUEkgZW4gSmF2YSBlc3QgdW4gc3VqZXQgZMOpasOgIGxhcmdlbWVudCB0cmFpdMOpLiBTaSB2b3VzIHV0aWxpc2V6IEplcnNleSBldCBzZXMgYW5ub3RhdGlvbnMsIHZvdXMgc2F2ZXogcXVlIGPigJllc3QgdHLDqHMgc2ltcGxlIGV0IHJhcGlkZSDDoCBtZXR0cmUgZW4gcGxhY2UuIERhbnMgY2V0IGFydGljbGUsIGplIHZvdWRyYWlzIHNpbXBsZW1lbnQgdm91cyBtb250cmVyIGNlIHF14oCZb24gcGV1dCBmYWlyZSBhdmVjIFZlcnQueCBlbiByZXZlbmFudCBhdXggZm9uZGFtZW50YXV4LiBFdCBlbiB2w6lyaXTDqSwgbGEgY3LDqWF0aW9uIGTigJl1bmUgQVBJIHNlcmEgYXZhbnQgdG91dCB1biBwcsOpdGV4dGUu")
                    .parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static JSONObject getTokenInformation(String jwt, String method) {
        if (method.equalsIgnoreCase("legacy")) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey("Q3LDqWVyIHVuZSBBUEkgZW4gSmF2YSBlc3QgdW4gc3VqZXQgZMOpasOgIGxhcmdlbWVudCB0cmFpdMOpLiBTaSB2b3VzIHV0aWxpc2V6IEplcnNleSBldCBzZXMgYW5ub3RhdGlvbnMsIHZvdXMgc2F2ZXogcXVlIGPigJllc3QgdHLDqHMgc2ltcGxlIGV0IHJhcGlkZSDDoCBtZXR0cmUgZW4gcGxhY2UuIERhbnMgY2V0IGFydGljbGUsIGplIHZvdWRyYWlzIHNpbXBsZW1lbnQgdm91cyBtb250cmVyIGNlIHF14oCZb24gcGV1dCBmYWlyZSBhdmVjIFZlcnQueCBlbiByZXZlbmFudCBhdXggZm9uZGFtZW50YXV4LiBFdCBlbiB2w6lyaXTDqSwgbGEgY3LDqWF0aW9uIGTigJl1bmUgQVBJIHNlcmEgYXZhbnQgdG91dCB1biBwcsOpdGV4dGUu")
                        .parseClaimsJws(jwt).getBody();
                return new JSONObject(claims);
            } catch (Exception e) {
                return null;
            }
        }
        if (method.equalsIgnoreCase("google")) {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo?id_token=" + jwt)
                    .queryParam("format", "json");

            HttpEntity<?> entity = new HttpEntity<>(headers);
            try {
                HttpEntity<String> response = restTemplate.exchange(
                        builder.build().encode().toUri(),
                        HttpMethod.GET,
                        entity,
                        String.class);
                System.out.println(response.getBody());
                Map<String, Object> google = new JSONObject(response.getBody()).toMap();
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dashboard?serverTimezone=UTC", "root", "");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id from users WHERE email = ?");
                preparedStatement.setString(1, (String) google.get("email"));
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                google.put("id", rs.getInt("id"));
                return new JSONObject(google);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private static Claims buildUserClaims(SLogin user, int id) {
        Claims claims = Jwts.claims();

        claims.put("id", id);
        claims.put("email", user.getEmail());
        claims.put("password", user.getPassword());

        return claims;
    }

    public static boolean verifyTokenGoogle(String token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo?id_token=" + token)
                .queryParam("format", "json");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        try {
            HttpEntity<String> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
