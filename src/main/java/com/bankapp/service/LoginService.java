package com.bankapp.service;

import com.bankapp.DAO.UserDAO;
import com.bankapp.model.User;

import java.util.Scanner;

public class LoginService {
    public static User login(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User user = UserDAO.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Invalid credentials. Try again.");
            return login(sc);  // recursive call
        }

        System.out.println("Login successful!");
        return user;
    }

}
