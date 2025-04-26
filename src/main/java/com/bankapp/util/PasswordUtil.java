package com.bankapp.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());  //salt ek type of random string hai jo plain pass me add ho jati hai, to save it from hackers(rainbow table)
    }
    public static boolean checkPassword(String password,String hashPassword){
        return BCrypt.checkpw(password, hashPassword);
    }
}
