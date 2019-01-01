package com.sidarenka.alien.validator;

public class UserValidator {

    public static boolean validateUserData(String login, String password, String email) {
        return LoginValidator.isLoginValid(login) && PasswordValidator.isPasswordValid(password)
                && EmailValidator.isEmailValid(email);
    }
}
