package com.cabletvbackend.constants;

public class CableTVConstants {

    public static final int SALT_LENGTH = 64;
    public static final int ITERATIONS = 1000;
    public static final int KEY_LENGTH = 512;
    public static final String ALGORITHM = "PBKDF2WithHmacSHA512";//PBKDF2WithHmacSHA512"
    public static final String SALT = "NJw1TFxao43p52oHy/n1vJvGXWA0wiVKy/4wYDxT/yzPEzZ13JcXaz+kZQIztGmyH8S/GUlKAUxZtr9hKvPh7Q==";
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
}
