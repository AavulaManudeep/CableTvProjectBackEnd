package com.cabletvbackend.password;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Component
public class PasswordUtils {

    private static final Logger logger = LogManager.getLogger("PasswordUtils");
    private static final SecureRandom RAND = new SecureRandom();
    private static final int ITERATIONS = 1000;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";//PBKDF2WithHmacSHA512"

    public PasswordUtils()
    {

    }
    public Optional<String> generateSalt(final int length)
    {
        if(length<1)
        {
            logger.fatal("Error in generating Salt, since the length is less than 1");
            Optional.empty();
        }
        byte[] salt = new byte[length];
        RAND.nextBytes(salt);
        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }

    public Optional<String> generateHashPassword(String password, String salt)
    {
        char[] charbytes_pass = password.toCharArray();
        byte [] bytes_salt = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(charbytes_pass,bytes_salt,ITERATIONS,KEY_LENGTH);
        Arrays.fill(charbytes_pass,Character.MIN_VALUE);
        try
        {
            SecretKeyFactory secratekey = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securepassword = secratekey.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securepassword));
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException ex)
        {
            logger.fatal("Error in generating HashPassword");
            return Optional.empty();
        }
        finally {
            spec.clearPassword();
        }
    }

    public boolean verifypassword(String password, String key, String salt)
    {
        Optional<String> password_check = generateHashPassword(password,salt);
        if(!password_check.isPresent())
        {
            return false;
        }
        return password_check.get().equals(key);

    }
}
