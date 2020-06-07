package com.cabletvbackend.JWT;

import com.cabletvbackend.constants.CableTVConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtill implements Serializable {

    private static final long serialVersionID = -224635382746L;

    public String generateToken(String userName, List<String> roles)
    {
        Map<String,Object> claims = new HashMap<>();
        if(!StringUtils.isEmpty(userName)){
            return Jwts.builder().setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis()+JwtConstants.TOKEN_EXP_TIME))
                    .setIssuedAt(new Date(System.currentTimeMillis())).claim("roles",roles)
                    .setHeaderParam("type",JwtConstants.TOKEN_TYPE)
                    .setIssuer(JwtConstants.TOKEN_ISSUER).setSubject(userName)
                    .signWith(SignatureAlgorithm.HS256,JwtConstants.TOKEN_SECRET)
                    .compact();
        }
        return null;
    }

    public <T> T extractClaims(String token,Function<Claims,T> claimsResolver)
    {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaims(String token)
    {
        return Jwts.parser().setSigningKey(JwtConstants.TOKEN_SECRET).parseClaimsJwt(token).getBody();
    }

    public String getUserName(String token) {
        return extractClaims(token,Claims::getSubject);
    }

    public Date getTokenExpirationTime(String token)
    {
        return extractClaims(token,Claims::getExpiration);
    }

    public boolean isTokenValid(String token)
    {
        final Date expiration = getTokenExpirationTime(token);
        return expiration.before(new Date());
    }

    public boolean ValidateToken(String token,String userName)
    {
        return getUserName(token).equals(userName) && !isTokenValid(token);
    }
}
