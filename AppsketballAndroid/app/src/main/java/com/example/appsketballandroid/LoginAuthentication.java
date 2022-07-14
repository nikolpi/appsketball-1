package com.example.appsketballandroid;

import java.util.HashMap;
import java.util.Map;

public class LoginAuthentication {


    public static boolean authenticate(String usernameInput, String passwordInput) {
        //Testing If the user's credential are the same with the db
        String url= "http://"+dbConnection.IP+"/PHP/appsketballPHP/APIs/CredentialRetrievalAPI.php?username=" + usernameInput;
        Map<String, String> userCredentials = new HashMap<>();
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            userCredentials = okHttpHandler.credentialRetrieval(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (passwordInput.equals(userCredentials.get("password"))) return true;

        return false;
    }
}
