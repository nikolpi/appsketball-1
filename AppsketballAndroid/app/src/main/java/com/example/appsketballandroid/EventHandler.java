package com.example.appsketballandroid;

import java.util.ArrayList;
import java.util.List;
//
// Not used anywhere yet. just take the url from here and use it with OkHttpHandler
//
public class EventHandler {

    public static List<Event> retrieveEvents(String game, int id) {
        String url = "http://" + dbConnection.IP + "/PHP/appsketballPHP/APIs/GameEventsRetrievalAPI.php?game=" + game + "&id=" + id;

        OkHttpHandler okHttpHandler = new OkHttpHandler();
        List<Event> events = new ArrayList<>();
        try {
            events = okHttpHandler.eventsRetrieval(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return events;
    }

}
