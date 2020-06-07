package com.cabletvbackend.dao;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.function.Function;

@Data
public class Authorites implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "USER";
    }

}
