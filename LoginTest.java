package com.mycompany.chatapp;

import java.util.regex.Pattern;

public class LoginTest {

    private String storedUsername;
    private String storedPassword;

    // Username: must contain "_" and be <= 5 chars
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Password: 8+ chars, uppercase, number, special char
    public boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,}$";
        return Pattern.matches(regex, password);
    }

    // SA number: must start with +27 and be 13 chars total
    public boolean checkCellPhoneNumber(String cellNumber) {
        String regex = "^\\+27\\d{9}$";
        return Pattern.matches(regex, cellNumber);
    }

    // Register user
    public String registerUser(String username, String password, String cellNumber) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        storedUsername = username;
        storedPassword = password;

        return "Registration successful";
    }

    // Login check
    public boolean loginUser(String username, String password) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }
}