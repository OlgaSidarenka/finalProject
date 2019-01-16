package com.sidarenka.alien.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class UserValidator.
 */
public class UserValidator {
    
    /** The pattern. */
    private static Pattern pattern;
    
    /** The matcher. */
    private static Matcher matcher;
    
    /** The Constant LOGIN_PATTERN. */
    private static final String LOGIN_PATTERN="^[a-zA-Z][a-zA-Z0-9-_]{4,20}$";
      
      /** The Constant PASSWORD_PATTERN. */
      private static final String PASSWORD_PATTERN="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}";
    
    /** The Constant EMAIL_PATTERN. */
    private static final String EMAIL_PATTERN = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";

    /**
     * Checks if is login valid.
     *
     * @param login the login
     * @return true, if is login valid
     */
    public static boolean isLoginValid (String login){
        pattern = Pattern.compile(LOGIN_PATTERN);
        matcher = pattern.matcher(login);
        return matcher.matches();
    }

    /**
     * Checks if is email valid.
     *
     * @param email the email
     * @return true, if is email valid
     */
    public static boolean isEmailValid(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    /**
     * Checks if is password valid.
     *
     * @param value the value
     * @return true, if is password valid
     */
    public static boolean isPasswordValid (String value){
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * Validate user data.
     *
     * @param login the login
     * @param password the password
     * @param email the email
     * @return true, if successful
     */
    public static boolean validateUserData(String login, String password, String email) {
        return isLoginValid(login) && isPasswordValid(password)&&isEmailValid(email);
    }
}
