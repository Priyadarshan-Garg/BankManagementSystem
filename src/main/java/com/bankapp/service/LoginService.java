package com.bankapp.service;

import com.bankapp.DAO.UserDAO;
import com.bankapp.model.User;
import com.bankapp.util.PasswordUtil;

import java.util.Scanner;

public class LoginService {
    private static final int MAX_ATTEMPTS = 3;  // maximum login attempts

    public static User login(String username, String password) {
        User user = UserDAO.getUserByUsername(username);
        String hashPassword = PasswordUtil.encryptPassword(password);
        
        if (user != null && !PasswordUtil.checkPassword(hashPassword,user.getPassword())) {
            return user;
        }
        return null;
    }

    // UI logic ko alag method mein rakha hai
    public static User loginWithRetry(Scanner sc) {
        int attempts = 0;
        User user = null;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            user = login(username, password);
            
            if (user != null) {
                System.out.println("Login successful!");
                return user;
            }

            attempts++;
            System.out.printf("Invalid credentials. %d attempts remaining.\n", 
                            MAX_ATTEMPTS - attempts);
        }

        System.out.println("Maximum login attempts exceeded.");
        return null;
    }
}