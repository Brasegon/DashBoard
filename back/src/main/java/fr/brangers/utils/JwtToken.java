package fr.brangers.utils;

import fr.brangers.controller.register.SerializeRegister;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtToken {

    public static String createToken(SerializeRegister user) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, "Q3LDqWVyIHVuZSBBUEkgZW4gSmF2YSBlc3QgdW4gc3VqZXQgZMOpasOgIGxhcmdlbWVudCB0cmFpdMOpLiBTaSB2b3VzIHV0aWxpc2V6IEplcnNleSBldCBzZXMgYW5ub3RhdGlvbnMsIHZvdXMgc2F2ZXogcXVlIGPigJllc3QgdHLDqHMgc2ltcGxlIGV0IHJhcGlkZSDDoCBtZXR0cmUgZW4gcGxhY2UuIERhbnMgY2V0IGFydGljbGUsIGplIHZvdWRyYWlzIHNpbXBsZW1lbnQgdm91cyBtb250cmVyIGNlIHF14oCZb24gcGV1dCBmYWlyZSBhdmVjIFZlcnQueCBlbiByZXZlbmFudCBhdXggZm9uZGFtZW50YXV4LiBFdCBlbiB2w6lyaXTDqSwgbGEgY3LDqWF0aW9uIGTigJl1bmUgQVBJIHNlcmEgYXZhbnQgdG91dCB1biBwcsOpdGV4dGUu")
                .setClaims(buildUserClaims(user))
                .setIssuedAt(new Date())
                .compact();
    }

    private static Claims buildUserClaims(SerializeRegister user) {
        Claims claims = Jwts.claims();

        claims.put("login", user.getLogin());
        claims.put("email", user.getEmail());
        claims.put("password", user.getPassword());

        return claims;
    }

}
