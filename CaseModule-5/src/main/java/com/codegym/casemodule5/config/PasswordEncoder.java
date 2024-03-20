package com.codegym.casemodule5.config;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordEncoder {

    public static String hashPassword(String plainTextPassword) {
        // Generate a salt for hashing
        String salt = BCrypt.gensalt();

        // Hash the password with the generated salt
        String hashedPassword = BCrypt.hashpw(plainTextPassword, salt);

        return hashedPassword;
    }

    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        // Use BCrypt to check if the plain text password matches the hashed password
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public static void main(String[] args) {
        // Example usage
        String plainTextPassword = "123456";

        // Hash the password
        String hashedPassword = hashPassword(plainTextPassword);
        System.out.println("Hashed Password: " + hashedPassword);

        // Verify the password
        boolean isPasswordCorrect = verifyPassword(plainTextPassword, hashedPassword);
        System.out.println("Is Password Correct? " + isPasswordCorrect);
    }
}
