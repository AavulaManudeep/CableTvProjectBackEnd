package com.cabletvbackend.JWT;

public final class JwtConstants {

    public static final String TOKEN_SECRET = "secret";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "CableTV";
    public static final long TOKEN_EXP_TIME = 5 * 60 * 60 * 1000;
    public static final String TOKEN_PREFIX = "Cable";
    public static final String TOKEN_HEADER = "Authorization";
    private JwtConstants()
    {
        throw new IllegalStateException("Cannot create instance of static class");
    }

}
