package com.wix.mediaplatform.v6.auth;

import com.wix.mediaplatform.v6.configuration.Configuration;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;
import java.util.List;

public class Authenticator {

    private final Configuration configuration;
    private final Key key;

    /**
     * @param configuration The Media Platform configuration
     */
    public Authenticator(Configuration configuration) {
        this.configuration = configuration;

        byte[] keyBytes = configuration.getSharedSecret().getBytes(Charset.forName("UTF-8"));
        this.key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    /**
     * @return The authorization header
     */
    public String getHeader() {
        Token token = new Token()
                .setIssuer(NS.APPLICATION + configuration.getAppId())
                .setSubject(NS.APPLICATION + configuration.getAppId());

        return getHeader(token);
    }

    /**
     * @param verbs a list of verbs to which the token will be limited
     * @return A limited authorization header
     * @throws IllegalArgumentException must provide at least one verb, otherwise the token will not be limited...
     */
    public String getHeader(List<String> verbs) {
        if (verbs.size() < 1) {
            throw new IllegalArgumentException("must provide at least one verb");
        }

        Token token = new Token()
                .setIssuer(NS.APPLICATION + configuration.getAppId())
                .setSubject(NS.APPLICATION + configuration.getAppId())
                .setVerbs(verbs);

        return getHeader(token);
    }

    /**
     * @param token an optional Token
     * @return The authorization header
     */
    public String getHeader(Token token) {
        return encode(token);
    }

    public String encode(Token token) {
        JwtBuilder builder = Jwts.builder().setClaims(token.toClaims()).signWith(key);

        return builder.compact();
    }

    public Token decode(String token) {
        try {
            Jws<Claims> jws = Jwts.parser()
                    .requireSubject(NS.APPLICATION + configuration.getAppId())
                    .setSigningKey(key)
                    .parseClaimsJws(token);

            return new Token(jws.getBody());
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException |
                ExpiredJwtException | IllegalArgumentException e) {
            throw new RuntimeException("invalid token", e);
        }
    }
}