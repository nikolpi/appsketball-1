package com.example.appsketballandroid.livestatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.appsketballandroid.Event;
import com.example.appsketballandroid.R;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LiveStatisticsActivity extends AppCompatActivity {

    List<Thread> threads = new ArrayList<>();
    Thread stThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_statistics);

        // RadioButtons
        RadioGroup rgQuarterButtons = findViewById(R.id.quarterRadioGroup);
        RadioButton gameRadioButton = (RadioButton) findViewById(R.id.gameRadioButton);
        RadioButton firstQuarterRadioButton = (RadioButton) findViewById(R.id.firstQuarterRadioButton);
        RadioButton secondQuarterRadioButton = (RadioButton) findViewById(R.id.secondQuarterRadioButton);
        RadioButton thirdQuarterRadioButton = (RadioButton) findViewById(R.id.thirdQuarterRadioButton);
        RadioButton fourthQuarterRadioButton = (RadioButton) findViewById(R.id.fourthQuarterRadioButton);


        String homeTeam = "ARIS";
        String awayTeam = "PAOK";
        EventsStorage gameEvents = new EventsStorage(homeTeam+"-"+awayTeam);

        // gameEvents.retrieveNewEvents(-1);

        // while(!gameEvents.getFourthQuarter().get(gameEvents.getFourthQuarter().size()-1).equals("STOP"))

        stThread = new statisticsRefreshThread(gameEvents, homeTeam, awayTeam, "game");
        stThread.start();
        rgQuarterButtons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                stThread.interrupt();

                switch (checkedId) {
                    case R.id.gameRadioButton:gameRadioButton.setOnClickListener((View v) -> {
                        stThread = new statisticsRefreshThread(gameEvents, homeTeam, awayTeam, "game");
                        stThread.start();
                    });
                        break;
                    case R.id.firstQuarterRadioButton:firstQuarterRadioButton.setOnClickListener((View v) -> {
                        stThread = new statisticsRefreshThread(gameEvents, homeTeam, awayTeam, "first");
                        stThread.start();
                    });
                        break;
                    case R.id.secondQuarterRadioButton:secondQuarterRadioButton.setOnClickListener((View v) -> {
                        stThread = new statisticsRefreshThread(gameEvents, homeTeam, awayTeam, "second");
                        stThread.start();
                    });
                        break;
                    case R.id.thirdQuarterRadioButton:thirdQuarterRadioButton.setOnClickListener((View v) -> {
                        stThread = new statisticsRefreshThread(gameEvents, homeTeam, awayTeam, "third");
                        stThread.start();
                    });
                        break;
                    case R.id.fourthQuarterRadioButton:fourthQuarterRadioButton.setOnClickListener((View v) -> {
                        stThread = new statisticsRefreshThread(gameEvents, homeTeam, awayTeam, "fourth");
                        stThread.start();
                    });
                        break;
                }
            }
        });


    }
    @Override
    public void onPause() {
        super.onPause();
        stThread.interrupt();
    }




    public class statisticsRefreshThread extends Thread {
        TextView homeFGATV = (TextView) findViewById(R.id.homeFGATV);
        TextView awayFGATV = (TextView) findViewById(R.id.awayFGATV);
        TextView homeFGMTV = (TextView) findViewById(R.id.homeFGMTV);
        TextView awayFGMTV = (TextView) findViewById(R.id.awayFGMTV);
        TextView homeFGPTV = (TextView) findViewById(R.id.homeFGPTV);
        TextView awayFGPTV = (TextView) findViewById(R.id.awayFGPTV);
        TextView homeFG1PATV = (TextView) findViewById(R.id.homeFG1PATV);
        TextView awayFG1PATV = (TextView) findViewById(R.id.awayFG1PATV);
        TextView homeFG1PMTV = (TextView) findViewById(R.id.homeFG1PMTV);
        TextView awayFG1PMTV = (TextView) findViewById(R.id.awayFG1PMTV);
        TextView homeFG2PATV = (TextView) findViewById(R.id.homeFG2PATV);
        TextView awayFG2PATV = (TextView) findViewById(R.id.awayFG2PATV);
        TextView homeFG2PMTV = (TextView) findViewById(R.id.homeFG2PMTV);
        TextView awayFG2PMTV = (TextView) findViewById(R.id.awayFG2PMTV);
        TextView homeFG3PATV = (TextView) findViewById(R.id.homeFG3PATV);
        TextView awayFG3PATV = (TextView) findViewById(R.id.awayFG3PATV);
        TextView homeFG3PMTV = (TextView) findViewById(R.id.homeFG3PMTV);
        TextView awayFG3PMTV = (TextView) findViewById(R.id.awayFG3PMTV);
        // Rebounds TextViews
        TextView homeReboundsTV = (TextView) findViewById(R.id.homeReboundsTV);
        TextView awayReboundsTV = (TextView) findViewById(R.id.awayReboundsTV);
        // Other TextViews
        TextView homeAssistsTV = (TextView) findViewById(R.id.homeAssistsTV);
        TextView awayAssistsTV = (TextView) findViewById(R.id.awayAssistsTV);
        TextView homeBlocksTV = (TextView) findViewById(R.id.homeBlocksTV);
        TextView awayBlocksTV = (TextView) findViewById(R.id.awayBlocksTV);
        TextView homeStealsTV = (TextView) findViewById(R.id.homeStealsTV);
        TextView awayStealsTV = (TextView) findViewById(R.id.awayStealsTV);
        TextView homeMistakesTV = (TextView) findViewById(R.id.homeMistakesTV);
        TextView awayMistakesTV = (TextView) findViewById(R.id.awayMistakesTV);
        TextView homeFoulsTV = (TextView) findViewById(R.id.homeFoulsTV);
        TextView awayFoulsTV = (TextView) findViewById(R.id.awayFoulsTV);

        private EventsStorage events;
        private String home;
        private String away;
        private String quarter;
        private List<Event> eventsList;

        public statisticsRefreshThread(EventsStorage events, String home, String away, String quarter) {
            this.events = events;
            this.home = home;
            this.away = away;
            this.quarter = quarter;

            events.getFirstQuarter().clear();
            events.getSecondQuarter().clear();
            events.getThirdQuarter().clear();
            events.getFourthQuarter().clear();
        }

        @Override
        public void run() {
            try {
                int lastId = -1;
                events.retrieveNewEvents(lastId);
                while (!isInterrupted()) {
                    eventsList = events.getAllQuartersFlatList();
                    if(quarter.equals("first")) eventsList = events.getFirstQuarter();
                    else if(quarter.equals("second")) eventsList = events.getSecondQuarter();
                    else if(quarter.equals("third")) eventsList = events.getThirdQuarter();
                    else if(quarter.equals("fourth")) eventsList = events.getFourthQuarter();
                    System.out.println("Mike 1 Hello from thread: " + Thread.currentThread());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Mike 2 Hello from the inside thread: " + Thread.currentThread());

                            homeFGATV.setText(String.valueOf(StatisticsCalculator.fieldGoalsAttempted(eventsList, home)));
                            awayFGATV.setText(String.valueOf(StatisticsCalculator.fieldGoalsAttempted(eventsList, away)));
                            homeFGMTV.setText(String.valueOf(StatisticsCalculator.fieldGoalsMade(eventsList, home)));
                            awayFGMTV.setText(String.valueOf(StatisticsCalculator.fieldGoalsMade(eventsList, away)));
                            homeFGPTV.setText(String.valueOf(StatisticsCalculator.fieldGoalsPercentage(eventsList, home)));
                            awayFGPTV.setText(String.valueOf(StatisticsCalculator.fieldGoalsPercentage(eventsList, away)));
                            homeFG1PATV.setText(String.valueOf(StatisticsCalculator.fieldGoals1PointAttempted(eventsList, home)));
                            awayFG1PATV.setText(String.valueOf(StatisticsCalculator.fieldGoals1PointAttempted(eventsList, away)));
                            homeFG1PMTV.setText(String.valueOf(StatisticsCalculator.fieldGoals1PointMade(eventsList, home)));
                            awayFG1PMTV.setText(String.valueOf(StatisticsCalculator.fieldGoals1PointMade(eventsList, away)));
                            homeFG2PATV.setText(String.valueOf(StatisticsCalculator.fieldGoals2PointAttempted(eventsList, home)));
                            awayFG2PATV.setText(String.valueOf(StatisticsCalculator.fieldGoals2PointAttempted(eventsList, away)));
                            homeFG2PMTV.setText(String.valueOf(StatisticsCalculator.fieldGoals2PointMade(eventsList, home)));
                            awayFG2PMTV.setText(String.valueOf(StatisticsCalculator.fieldGoals2PointMade(eventsList, away)));
                            homeFG3PATV.setText(String.valueOf(StatisticsCalculator.fieldGoals3PointAttempted(eventsList, home)));
                            awayFG3PATV.setText(String.valueOf(StatisticsCalculator.fieldGoals3PointAttempted(eventsList, away)));
                            homeFG3PMTV.setText(String.valueOf(StatisticsCalculator.fieldGoals3PointMade(eventsList, home)));
                            awayFG3PMTV.setText(String.valueOf(StatisticsCalculator.fieldGoals3PointMade(eventsList, away)));

                            homeReboundsTV.setText(String.valueOf(StatisticsCalculator.rebounds(eventsList, home)));
                            awayReboundsTV.setText(String.valueOf(StatisticsCalculator.rebounds(eventsList, away)));

                            homeAssistsTV.setText(String.valueOf(StatisticsCalculator.assists(eventsList, home)));
                            awayAssistsTV.setText(String.valueOf(StatisticsCalculator.assists(eventsList, away)));
                            homeBlocksTV.setText(String.valueOf(StatisticsCalculator.blocks(eventsList, home)));
                            awayBlocksTV.setText(String.valueOf(StatisticsCalculator.blocks(eventsList, away)));
                            homeStealsTV.setText(String.valueOf(StatisticsCalculator.steals(eventsList, home)));
                            awayStealsTV.setText(String.valueOf(StatisticsCalculator.steals(eventsList, away)));
                            homeMistakesTV.setText(String.valueOf(StatisticsCalculator.mistakes(eventsList, home)));
                            awayMistakesTV.setText(String.valueOf(StatisticsCalculator.mistakes(eventsList, away)));
                            homeFoulsTV.setText(String.valueOf(StatisticsCalculator.fouls(eventsList, home)));
                            awayFoulsTV.setText(String.valueOf(StatisticsCalculator.fouls(eventsList, away)));
                        }
                    });
                    for (Event event :
                            eventsList) {
                        if(lastId < event.getId()) lastId = event.getId();
                    }

                    events.retrieveNewEvents(lastId);
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}