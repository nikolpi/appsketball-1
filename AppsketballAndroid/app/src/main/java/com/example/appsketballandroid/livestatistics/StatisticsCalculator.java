package com.example.appsketballandroid.livestatistics;

import com.example.appsketballandroid.Event;

import java.util.List;

public class StatisticsCalculator {

    public static int fieldGoalsAttempted(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().matches("SHOT_2_IN|SHOT_2_OUT|SHOT_3_IN|SHOT_3_OUT")) {
                count++;
            }
        }
        return count;
    }

    public static int fieldGoalsMade(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().matches("SHOT_2_IN|SHOT_3_IN")) {
                count++;
            }
        }
        return count;
    }

    public static double fieldGoalsPercentage(List<Event> events, String team) {
        if (fieldGoalsAttempted(events, team) == 0) return 0.0;
        return (fieldGoalsMade(events, team) * 100) / fieldGoalsAttempted(events, team);
    }

    public static int fieldGoals1PointAttempted(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().matches("SHOT_1_IN|SHOT_1_OUT")) {
                count++;
            }
        }
        return count;
    }

    public static int fieldGoals1PointMade(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().equals("SHOT_1_IN")) {
                count++;
            }
        }
        return count;
    }

    public static int fieldGoals2PointAttempted(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().matches("SHOT_2_IN|SHOT_2_OUT")) {
                count++;
            }
        }
        return count;
    }

    public static int fieldGoals2PointMade(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().equals("SHOT_2_IN")) {
                count++;
            }
        }
        return count;
    }

    public static int fieldGoals3PointAttempted(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().matches("SHOT_3_IN|SHOT_3_OUT")) {
                count++;
            }
        }
        return count;
    }

    public static int fieldGoals3PointMade(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().equals("SHOT_3_IN")) {
                count++;
            }
        }
        return count;
    }

    public static int rebounds(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().equals("REBOUND")) {
                count++;
            }
        }
        return count;
    }

    public static int assists(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().equals("ASSIST")) {
                count++;
            }
        }
        return count;
    }

    public static int blocks(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().equals("BLOCK")) {
                count++;
            }
        }
        return count;
    }

    public static int steals(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().equals("STEAL")) {
                count++;
            }
        }
        return count;
    }

    public static int mistakes(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().equals("MISTAKE")) {
                count++;
            }
        }
        return count;
    }

    public static int fouls(List<Event> events, String team) {
        int count = 0;
        for (Event event :
                events) {
            if(team.equals(event.getTeam()) && event.getEventAction().equals("FOUL")) {
                count++;
            }
        }
        return count;
    }




}
