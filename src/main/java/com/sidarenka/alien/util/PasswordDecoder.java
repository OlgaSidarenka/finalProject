package com.sidarenka.alien.util;

import org.apache.commons.codec.binary.Base64;

/**
 * The Class PasswordDecoder.
 */
public class PasswordDecoder {
    
    /**
     * Decode password.
     *
     * @param password the password
     * @return the string
     */
    public static String decodePassword(String password){
        Base64 base64=new Base64();
        String decodedPassword= new String(base64.decode(password.getBytes()));
        return decodedPassword;
    }
}
