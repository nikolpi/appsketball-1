package com.example.appsketballandroid;

import java.util.Date;

//enum EventAction {
//    SHOT_1_IN,
//    SHOT_2_IN,
//    SHOT_3_IN,
//    SHOT_1_OUT,
//    SHOT_2_OUT,
//    SHOT_3_OUT,
//    REBOUND,
//    ASSIST,
//    BLOCK,
//    STEAL,
//    MISTAKE,
//    FOUL
//}

public class Event {

    private int id;
    private String date;
    private String game;
    private String playerName;
    private String eventAction;
    private int quarter;
    private String team;

    public Event(int id, String date, String game, String playerName, String eventAction, int quarter, String team) {
        this.id = id;
        this.date = date;
        this.game = game;
        this.playerName = playerName;
        this.eventAction = eventAction;
        this.quarter = quarter;
        this.team = team;

    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", game='" + game + '\'' +
                ", playerName='" + playerName + '\'' +
                ", eventAction='" + eventAction + '\'' +
                ", quarter=" + quarter +
                ", team='" + team + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getGame() {
        return game;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getEventAction() {
        return eventAction;
    }

    public int getQuarter() {
        return quarter;
    }

    public String getTeam() {
        return team;
    }
}
