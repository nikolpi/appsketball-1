package com.example.appsketballandroid;

public class Team {

    private int id;
    private String name;
    private String badge;
    private String town;

    public Team(int id, String name, String badge, String town) {
        this.id = id;
        this.name = name;
        this.badge = badge;
        this.town = town;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBadge() {
        return badge;
    }

    public String getTown() {
        return town;
    }
}
