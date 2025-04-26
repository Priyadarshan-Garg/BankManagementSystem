package com.bankapp.service;

import com.bankapp.DAO.UserDAO;
import com.bankapp.model.User;
import com.bankapp.util.PasswordUtil;

import java.io.StringReader;
import java.util.Scanner;


public class AuthService {
public static User register(Scanner sc) {
    System.out.print("Enter username: ");
    String username = sc.nextLine();
    System.out.print("Enter password: ");
    String password = sc.nextLine();

    User user = new User();
    user.setUserName(username);
    String hashPassword = PasswordUtil.encryptPassword(password);
    user.setPassword(hashPassword);

    // Pehle check karo username unique hai kya
    if (UserDAO.getUserByUsername(username) != null) {
        System.out.println("Username already exists!");
        return register(sc);  // recursive call
    }

    UserDAO.saveOrUpdate(user); // DB me save kar
    System.out.println("Registration successful!");
    return user;
}

}
