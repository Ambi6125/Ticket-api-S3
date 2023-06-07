package sem3.project.individual.business.implementors.login;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sem3.project.individual.business.AccessTokenDecoder;
import sem3.project.individual.business.AccessTokenEncoder;
import sem3.project.individual.business.exceptions.InvalidTokenException;
import sem3.project.individual.domain.login.tokens.AccessToken;

import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TokenEncryptor implements AccessTokenEncoder, AccessTokenDecoder
{
    private final Key key;

    public TokenEncryptor(@Value("${jwt.secret}") String secretKey)
    {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public AccessToken decode(String encodedToken)
    {
        try
        {
            Jwt jwt = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parse(encodedToken);
            Claims claims = (Claims) jwt.getBody();

            List<String> roles = claims.get("roles", List.class);

            return AccessToken.builder()
                    .subject(claims.getSubject())
                    .roles(roles)
                    .accountId(claims.get("accountId", Long.class))
                    .build();
        }
        catch (JwtException e)
        {
            throw new InvalidTokenException(e.getMessage());
        }
    }

    @Override
    public String encode(AccessToken token)
    {
        Map<String, Object> claims = new HashMap<>();
        if(!CollectionUtils.isEmpty(token.getRoles()))
        {
            claims.put("roles", token.getRoles());
        }

        if(token.getAccountId() != null)
        {
            claims.put("accountId", token.getAccountId());
        }

        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(token.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claims)
                .signWith(key)
                .compact();
    }
}
