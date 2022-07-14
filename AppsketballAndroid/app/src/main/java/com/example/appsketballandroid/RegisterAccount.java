package com.example.appsketballandroid;

import java.util.HashMap;
import java.util.Map;

public class RegisterAccount {


    public static void register(String usernameInput, String emailInput, String passwordInput, Boolean isAdminInput)
    {


        String url = "http://" + dbConnection.IP + "/PHP/appsketballPHP/APIs/RegisterAPI.php?id=&username="+usernameInput+"&email="+emailInput+"&password="+passwordInput+"&isAdmin="+isAdminInput;

        OkHttpHandler okHttpHandler = new OkHttpHandler();
        try {
            okHttpHandler.register(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean checkIfRegistered(String usernameInput)
    {
        String url= "http://"+dbConnection.IP+"/PHP/appsketballPHP/APIs/CredentialRetrievalAPI.php?username=" + usernameInput;

        Map<String, String> userCredentials = new HashMap<>();
        // To check
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            userCredentials = okHttpHandler.credentialRetrieval(url);
            if(userCredentials.size() != 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }


}
