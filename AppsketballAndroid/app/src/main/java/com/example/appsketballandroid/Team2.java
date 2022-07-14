package com.example.appsketballandroid;


import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

public class Team2 extends AppCompatActivity {

    private String teamName;
    private List<HappenedEvents> periods=new ArrayList<HappenedEvents>();

    public Team2(String teamName) {

        this.teamName = teamName;
        for(int i=0;i<4;i++)
        {
            periods.add(new HappenedEvents());
        }
    }

    public void addToPeriods(Event event)
    {
        int period = event.getQuarter()-1;
            if(event.getEventAction().equals("SHOT_1_IN"))
            {
                periods.get(period).setSHOT_1_IN( (periods.get(period).getSHOT_1_IN())+1);

            }else if(event.getEventAction().equals("SHOT_1_OUT"))
            {
                periods.get(period).setSHOT_1_OUT( (periods.get(period).getSHOT_1_OUT())+1);

            }else if(event.getEventAction().equals("SHOT_2_IN"))
            {
                periods.get(period).setSHOT_2_IN( (periods.get(period).getSHOT_2_IN())+1);

            }else if(event.getEventAction().equals("SHOT_2_OUT"))
            {
                periods.get(period).setSHOT_2_OUT( (periods.get(period).getSHOT_2_OUT())+1);

            }else if(event.getEventAction().equals("SHOT_3_IN"))
            {
                periods.get(period).setSHOT_3_IN( (periods.get(period).getSHOT_3_IN())+1);

            }else if(event.getEventAction().equals("SHOT_3_OUT"))
            {
                periods.get(period).setSHOT_3_OUT( (periods.get(period).getSHOT_3_OUT())+1);

            }else if(event.getEventAction().equals("ASSIST"))
            {
                periods.get(period).setASSIST( (periods.get(period).getASSIST())+1);

            }else if(event.getEventAction().equals("BLOCK"))
            {
                periods.get(period).setBLOCK( (periods.get(period).getBLOCK())+1);

            }else if(event.getEventAction().equals("STEAL"))
            {
                periods.get(period).setSTEAL( (periods.get(period).getSTEAL())+1);

            }else if(event.getEventAction().equals("FOUL"))
            {
                periods.get(period).setFOUL( (periods.get(period).getFOUL())+1);

            }else if(event.getEventAction().equals("REBOUND"))
            {
                periods.get(period).setREBOUND( (periods.get(period).getREBOUND())+1);

            }else
            {
                periods.get(period).setMISTAKE( (periods.get(period).getMISTAKE())+1);
            }
    }

    public String getTeamName() {
        return teamName;
    }

    public void printData(int p)
    {

        System.out.println("Team:"+this.teamName+periods.get(p).toString() ) ;

    }
    public HappenedEvents getPeriods(int i) {
        return periods.get(i);
    }
}
