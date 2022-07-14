package com.example.appsketballandroid.livestatistics;

import com.example.appsketballandroid.Event;
import com.example.appsketballandroid.OkHttpHandler;
import com.example.appsketballandroid.dbConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventsStorage {
    private String game;
    private List<Event> firstQuarter;
    private List<Event> secondQuarter;
    private List<Event> thirdQuarter;
    private List<Event> fourthQuarter;
    private List<List<Event>> allQuarters;


    public EventsStorage(String game) {
        this.game = game;
        firstQuarter = new ArrayList<>();
        secondQuarter = new ArrayList<>();
        thirdQuarter = new ArrayList<>();
        fourthQuarter = new ArrayList<>();
        allQuarters = new ArrayList<>();
        allQuarters.add(firstQuarter);
        allQuarters.add(secondQuarter);
        allQuarters.add(thirdQuarter);
        allQuarters.add(fourthQuarter);
    }


    public void retrieveNewEvents(int id) {
        String url = "http://" + dbConnection.IP + "/PHP/appsketballPHP/APIs/GameEventsRetrievalAPI.php?game=" + game + "&id=" + id;

        OkHttpHandler okHttpHandler = new OkHttpHandler();
        try {
            List<Event> newEvents = okHttpHandler.eventsRetrieval(url);

            for (Event event:newEvents) {
                if (event.getQuarter() == 1) {
                    firstQuarter.add(event);
                }
                else if (event.getQuarter() == 2) {
                    secondQuarter.add(event);
                }
                else if (event.getQuarter() == 3) {
                    thirdQuarter.add(event);
                }
                else if (event.getQuarter() == 4) {
                    fourthQuarter.add(event);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Event> getAllQuartersFlatList() {
        return allQuarters.stream().flatMap(List::stream).collect(Collectors.toList());
    }


    public String getGame() {
        return game;
    }

    public List<Event> getFirstQuarter() {
        return firstQuarter;
    }

    public List<Event> getSecondQuarter() {
        return secondQuarter;
    }

    public List<Event> getThirdQuarter() {
        return thirdQuarter;
    }

    public List<Event> getFourthQuarter() {
        return fourthQuarter;
    }

    public List<List<Event>> getAllQuarters() {
        return allQuarters;
    }
}
