package com.example.appsketballandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AfterMatchStat extends AppCompatActivity {

    public static List<Event> allEvents =new ArrayList<>();
    public static List<Team2> teams = new ArrayList<>();
    private Button periodOne,periodTwo;
    private int scoreRight_1,scoreLeft_1,scoreLeft_2,scoreRight_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_match_stat_activity);


        EventHandler eventHandler = new EventHandler();
        //Get all the events from the game
        allEvents =eventHandler.retrieveEvents("ARIS-PAOK",-1);
        String teamsname= allEvents.get(0).getGame();
        String[] splitted = teamsname.split("-");
        String teamLeftName=splitted[0];
        String teamRightName=splitted[1];
        Team2 teamLeft = new Team2(teamLeftName);
        Team2 teamRight = new Team2(teamRightName);

        teams.add(teamLeft);
        teams.add(teamRight);

        TextView teamsPlayingTv = (TextView)findViewById(R.id.teamsPlaying_tv);
        teamsPlayingTv.setText(teamsname);

        for(int i = 0; i<allEvents.size(); i++)
        {
            if(allEvents.get(i).getTeam().equals(teamLeft.getTeamName()))
            {
                teams.get(0).addToPeriods( allEvents.get(i) );
            }else
            {
                teams.get(1).addToPeriods( allEvents.get(i) );
            }

        }
        periodOne = findViewById(R.id.firstPeriod_btn);
        periodOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //3 First Team
                TextView text3INL_1 = findViewById(R.id.left_three_goals_made);
                int value3inl_1= teams.get(0).getPeriods(0).getSHOT_3_IN();
                text3INL_1.setText( String.valueOf(value3inl_1) );
                ProgressBar progressBar3INL_1 = findViewById(R.id.left_progress_three_goals_made);
                progressBar3INL_1.setProgress(value3inl_1);

                int value3outl_1= teams.get(0).getPeriods(0).getSHOT_3_OUT();

                int sum3ATTL_1=value3outl_1+value3inl_1;

                TextView text3AttL_1 = findViewById(R.id.left_three_goal_attempted);
                text3AttL_1.setText(String.valueOf(sum3ATTL_1));
                ProgressBar progressBar3ATTL_1 = findViewById(R.id.left_progress_three_goals_attempted);
                progressBar3ATTL_1.setProgress(sum3ATTL_1);

                //2 First Team
                TextView text2INL_1 = findViewById(R.id.left_two_goals_made);
                int value2inl_1= teams.get(0).getPeriods(0).getSHOT_2_IN();
                text2INL_1.setText( String.valueOf(value2inl_1) );
                ProgressBar progressBar2INL_1 = findViewById(R.id.left_progress_two_points_made);
                progressBar2INL_1.setProgress(value2inl_1);

                int value2outl_1= teams.get(0).getPeriods(0).getSHOT_2_OUT();
                int sum2ATTL_1=value2outl_1+value2inl_1;

                TextView text2AttL_1 = findViewById(R.id.left_two_goals_attempted);
                text2AttL_1.setText(String.valueOf(sum2ATTL_1));
                ProgressBar progressBar2ATTL_1 = findViewById(R.id.left_progress_two_goals_attempted);
                progressBar2ATTL_1.setProgress(sum2ATTL_1);

                //1
                TextView text1INL_1 = findViewById(R.id.left_free_throws_made);
                int value1inl_1= teams.get(0).getPeriods(0).getSHOT_1_IN();
                text1INL_1.setText( String.valueOf(value1inl_1) );
                ProgressBar progressBar1INL_1 = findViewById(R.id.left_progress_free_throws_made);
                progressBar1INL_1.setProgress(value1inl_1);

                int value1outl_1= teams.get(0).getPeriods(0).getSHOT_1_OUT();
                int sum1ATTL_1=value1outl_1+value1inl_1;

                TextView text1AttL_1 = findViewById(R.id.left_free_throws_attempted);
                text1AttL_1.setText(String.valueOf(sum1ATTL_1));
                ProgressBar progressBarATTL_1 = findViewById(R.id.left_progress_free_throws_attempted);
                progressBarATTL_1.setProgress(sum1ATTL_1);

                //Score
                scoreLeft_1 = 3*value3inl_1 + 2*value2inl_1 + value1inl_1;
                TextView leftScore = findViewById(R.id.leftScore);
                leftScore.setText(String.valueOf(scoreLeft_1));

                //Field
                int sumGoalsAttL_1 = sum3ATTL_1+sum2ATTL_1+sum1ATTL_1;
                TextView textATT_1L = findViewById(R.id.left_goals_attempted);
                textATT_1L.setText( String.valueOf(sumGoalsAttL_1));
                ProgressBar progressBarAtt_1L = findViewById(R.id.left_progress_goals_attempted);
                progressBarAtt_1L.setProgress(sumGoalsAttL_1);

                int sumGoalsMadeL_1 = value3inl_1+value2inl_1+value1inl_1;
                TextView textMADE_1L = findViewById(R.id.left_goals_made);
                textMADE_1L.setText( String.valueOf(sumGoalsMadeL_1));
                ProgressBar progressaBarMade_1L = findViewById(R.id.left_progress_goals_made);
                progressaBarMade_1L.setProgress(sumGoalsMadeL_1);

                TextView perc_1L = findViewById(R.id.left_goals_perc);
                int perc_1l = perc(sumGoalsAttL_1,sumGoalsMadeL_1);
                perc_1L.setText(String.valueOf(perc_1l));
                ProgressBar progressaBarPerc_1L = findViewById(R.id.left_progress_goals_perc);
                progressaBarPerc_1L.setProgress(perc_1l);

                //Assists
                TextView assists_1L = findViewById(R.id.left_assists);
                int assists_1l = teams.get(0).getPeriods(0).getASSIST();
                assists_1L.setText(String.valueOf(assists_1l));
                ProgressBar progressBarAssists_1L = findViewById(R.id.assists_lpb);
                progressBarAssists_1L.setProgress(assists_1l);

                //Block
                TextView blocks_1L = findViewById(R.id.left_blocks);
                int blocks_1l = teams.get(0).getPeriods(0).getBLOCK();
                blocks_1L.setText(String.valueOf(blocks_1l));
                ProgressBar progressBarBlock_1L = findViewById(R.id.blocks_lpb);
                progressBarBlock_1L.setProgress(blocks_1l);

                //Foul
                TextView fouls_1L = findViewById(R.id.left_fouls);
                int fouls_1l = teams.get(0).getPeriods(0).getASSIST();
                fouls_1L.setText(String.valueOf(fouls_1l));
                ProgressBar progressBarFouls_1L = findViewById(R.id.fouls_lpb);
                progressBarFouls_1L.setProgress(fouls_1l);

                //Mistakes
                TextView mistakes_1L = findViewById(R.id.left_mistakes);
                int mistakes_1l = teams.get(0).getPeriods(0).getMISTAKE();
                mistakes_1L.setText(String.valueOf(mistakes_1l));
                ProgressBar progressBarMistakes_1L = findViewById(R.id.mistakes_lpb);
                progressBarMistakes_1L.setProgress(mistakes_1l);

                //Rebounds
                TextView rebounds_1L = findViewById(R.id.left_rebounds);
                int rebounds_1l = teams.get(0).getPeriods(0).getREBOUND();
                rebounds_1L.setText(String.valueOf(rebounds_1l));
                ProgressBar progressBarRebouds_1L = findViewById(R.id.rebounds_lpb);
                progressBarRebouds_1L.setProgress(rebounds_1l);

                //Steals
                TextView steals_1L = findViewById(R.id.left_steals);
                int steals_1l = teams.get(0).getPeriods(0).getSTEAL();
                steals_1L.setText(String.valueOf(steals_1l));
                ProgressBar progressBarSteals_1L = findViewById(R.id.steals_lpb);
                progressBarSteals_1L.setProgress(steals_1l);


                //Team 2
                //3
                TextView text3INR_1 = findViewById(R.id.right_three_goals_made);
                int value3inr_1= teams.get(1).getPeriods(0).getSHOT_3_IN();
                text3INR_1.setText( String.valueOf(value3inr_1) );
                ProgressBar progressBar3INR_1 = findViewById(R.id.right_progress_three_goals_made);
                progressBar3INR_1.setProgress(value3inr_1);

                int value3outr_1= teams.get(1).getPeriods(0).getSHOT_3_OUT();

                int sum3ATTR_1=value3outr_1+value3inr_1;

                TextView text3AttR_1 = findViewById(R.id.right_three_goals_attem);
                text3AttR_1.setText(String.valueOf(sum3ATTR_1));
                ProgressBar progressBar3ATTR_1 = findViewById(R.id.right_progress_three_goals_attempted);
                progressBar3ATTR_1.setProgress(sum3ATTR_1);

                //2
                TextView text2INR_1 = findViewById(R.id.right_two_point_made);
                int value2inr_1= teams.get(1).getPeriods(0).getSHOT_2_IN();
                text2INR_1.setText( String.valueOf(value2inr_1) );
                ProgressBar progressBar2INR_1 = findViewById(R.id.right_progress_two_points_made);
                progressBar2INR_1.setProgress(value2inr_1);

                int value2outr_1= teams.get(1).getPeriods(0).getSHOT_2_OUT();
                int sum2ATTR_1=value2outr_1+value2inr_1;

                TextView text2AttR_1 = findViewById(R.id.right_two_point_attempted);
                text2AttR_1.setText(String.valueOf(sum2ATTR_1));
                ProgressBar progressBar2ATTR_1 = findViewById(R.id.right_progress_two_points_attempted);
                progressBar2ATTR_1.setProgress(sum2ATTR_1);

                //1
                TextView text1INR_1 = findViewById(R.id.right_free_throws_made);
                int value1inr_1= teams.get(1).getPeriods(0).getSHOT_1_IN();
                text1INR_1.setText( String.valueOf(value1inr_1) );
                ProgressBar progressBar1INR_1 = findViewById(R.id.right_progress_free_throws_made);
                progressBar1INR_1.setProgress(value1inr_1);

                int value1outr_1= teams.get(1).getPeriods(0).getSHOT_1_OUT();
                int sum1ATTR_1=value1outr_1+value1inr_1;

                TextView text1AttR_1 = findViewById(R.id.right_free_throws_attempted);
                text1AttR_1.setText(String.valueOf(sum1ATTR_1));
                ProgressBar progressBarATTR_1 = findViewById(R.id.right_progress_free_throws_attempted);
                progressBarATTR_1.setProgress(sum1ATTR_1);

                //Score
                scoreRight_1 = 3*value3inr_1 + 2*value2inr_1 + value1inr_1;
                TextView rightScore = findViewById(R.id.rightScore);
                rightScore.setText(String.valueOf(scoreRight_1));

                //Field
                int sumGoalsAttR_1 = sum3ATTR_1+sum2ATTR_1+sum1ATTR_1;
                TextView textATT_1R = findViewById(R.id.right_goals_attempted);
                textATT_1R.setText( String.valueOf(sumGoalsAttR_1));
                ProgressBar progressBarAtt_1R = findViewById(R.id.right_progress_goals_attempted);
                progressBarAtt_1R.setProgress(sumGoalsAttR_1);

                int sumGoalsMadeR_1 = value3inr_1+value2inr_1+value1inr_1;
                TextView textMADE_1R = findViewById(R.id.right_goals_made);
                textMADE_1R.setText( String.valueOf(sumGoalsMadeR_1));
                ProgressBar progressaBarMade_1R = findViewById(R.id.right_progress_goals_made);
                progressaBarMade_1R.setProgress(sumGoalsMadeR_1);

                TextView perc_1R = findViewById(R.id.right_goals_perc);
                int perc_1r = perc(sumGoalsAttR_1,sumGoalsMadeR_1);
                perc_1R.setText(String.valueOf(perc_1r));
                ProgressBar progressaBarPerc_1R = findViewById(R.id.right_progress_goals_perc);
                progressaBarPerc_1R.setProgress(perc_1r);

                //Assists
                TextView assists_1R = findViewById(R.id.right_assists);
                int assists_1r = teams.get(1).getPeriods(0).getASSIST();
                assists_1R.setText(String.valueOf(assists_1r));
                ProgressBar progressBarAssists_1R = findViewById(R.id.assists_rpb);
                progressBarAssists_1R.setProgress(assists_1r);

                //Block
                TextView blocks_1R = findViewById(R.id.right_blocks);
                int blocks_1r = teams.get(1).getPeriods(0).getBLOCK();
                blocks_1R.setText(String.valueOf(blocks_1r));
                ProgressBar progressBarBlock_1R = findViewById(R.id.blocks_rpb);
                progressBarBlock_1R.setProgress(blocks_1r);

                //Foul
                TextView fouls_1R = findViewById(R.id.right_fouls);
                int fouls_1r = teams.get(1).getPeriods(0).getASSIST();
                fouls_1R.setText(String.valueOf(fouls_1r));
                ProgressBar progressBarFouls_1R = findViewById(R.id.fouls_rpb);
                progressBarFouls_1R.setProgress(fouls_1r);

                //Mistakes
                TextView mistakes_1R = findViewById(R.id.right_mistakes);
                int mistakes_1r = teams.get(1).getPeriods(0).getMISTAKE();
                mistakes_1R.setText(String.valueOf(mistakes_1r));
                ProgressBar progressBarMistakes_1R = findViewById(R.id.mistakes_rpb);
                progressBarMistakes_1R.setProgress(mistakes_1r);

                //Rebounds
                TextView rebounds_1R = findViewById(R.id.rigth_rebounds);
                int rebounds_1r = teams.get(1).getPeriods(0).getREBOUND();
                rebounds_1R.setText(String.valueOf(rebounds_1r));
                ProgressBar progressBarRebouds_1R = findViewById(R.id.rebounds_rpb);
                progressBarRebouds_1R.setProgress(rebounds_1r);

                //Steals
                TextView steals_1R = findViewById(R.id.right_steals);
                int steals_1r = teams.get(1).getPeriods(0).getSTEAL();
                steals_1R.setText(String.valueOf(steals_1r));
                ProgressBar progressBarSteals_1R = findViewById(R.id.steals_rpb);
                progressBarSteals_1R.setProgress(steals_1r);
            }
        });//period1Listener

        periodTwo=findViewById(R.id.secondPeriod_btn);
        periodTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //3 First Team
                TextView text3INL_2 = findViewById(R.id.left_three_goals_made);
                int value3inl_2= teams.get(0).getPeriods(1).getSHOT_3_IN();
                text3INL_2.setText( String.valueOf(value3inl_2) );
                ProgressBar progressBar3INL_2 = findViewById(R.id.left_progress_three_goals_made);
                progressBar3INL_2.setProgress(value3inl_2);

                int value3outl_2= teams.get(0).getPeriods(1).getSHOT_3_OUT();

                int sum3ATTL_2=value3outl_2+value3inl_2;

                TextView text3AttL_2 = findViewById(R.id.left_three_goal_attempted);
                text3AttL_2.setText(String.valueOf(sum3ATTL_2));
                ProgressBar progressBar3ATTL_2 = findViewById(R.id.left_progress_three_goals_attempted);
                progressBar3ATTL_2.setProgress(sum3ATTL_2);

                //2 First Team
                TextView text2INL_2 = findViewById(R.id.left_two_goals_made);
                int value2inl_2= teams.get(0).getPeriods(1).getSHOT_2_IN();
                text2INL_2.setText( String.valueOf(value2inl_2) );
                ProgressBar progressBar2INL_2 = findViewById(R.id.left_progress_two_points_made);
                progressBar2INL_2.setProgress(value2inl_2);

                int value2outl_2= teams.get(0).getPeriods(1).getSHOT_2_OUT();
                int sum2ATTL_2=value2outl_2+value2inl_2;

                TextView text2AttL_2 = findViewById(R.id.left_two_goals_attempted);
                text2AttL_2.setText(String.valueOf(sum2ATTL_2));
                ProgressBar progressBar2ATTL_2 = findViewById(R.id.left_progress_two_goals_attempted);
                progressBar2ATTL_2.setProgress(sum2ATTL_2);

                //1
                TextView text1INL_2 = findViewById(R.id.left_free_throws_made);
                int value1inl_2= teams.get(0).getPeriods(1).getSHOT_1_IN();
                text1INL_2.setText( String.valueOf(value1inl_2) );
                ProgressBar progressBar1INL_2 = findViewById(R.id.left_progress_free_throws_made);
                progressBar1INL_2.setProgress(value1inl_2);

                int value1outl_2= teams.get(0).getPeriods(1).getSHOT_1_OUT();
                int sum1ATTL_2=value1outl_2+value1inl_2;

                TextView text1AttL_2 = findViewById(R.id.left_free_throws_attempted);
                text1AttL_2.setText(String.valueOf(sum1ATTL_2));
                ProgressBar progressBarATTL_2 = findViewById(R.id.left_progress_free_throws_attempted);
                progressBarATTL_2.setProgress(sum1ATTL_2);

                //Score
                scoreLeft_2 = 3*value3inl_2 + 2*value2inl_2 + value1inl_2 + scoreLeft_1;
                TextView leftScore = findViewById(R.id.leftScore);
                leftScore.setText(String.valueOf(scoreLeft_2));

                //Field
                int sumGoalsAttL_2 = sum3ATTL_2+sum2ATTL_2+sum1ATTL_2;
                TextView textATT_2L = findViewById(R.id.left_goals_attempted);
                textATT_2L.setText( String.valueOf(sumGoalsAttL_2));
                ProgressBar progressBarAtt_2L = findViewById(R.id.left_progress_goals_attempted);
                progressBarAtt_2L.setProgress(sumGoalsAttL_2);

                int sumGoalsMadeL_2 = value3inl_2+value2inl_2+value1inl_2;
                TextView textMADE_2L = findViewById(R.id.left_goals_made);
                textMADE_2L.setText( String.valueOf(sumGoalsMadeL_2));
                ProgressBar progressaBarMade_2L = findViewById(R.id.left_progress_goals_made);
                progressaBarMade_2L.setProgress(sumGoalsMadeL_2);

                TextView perc_2L = findViewById(R.id.left_goals_perc);
                int perc_2l = perc(sumGoalsAttL_2,sumGoalsMadeL_2);
                perc_2L.setText(String.valueOf(perc_2l));
                ProgressBar progressaBarPerc_2L = findViewById(R.id.left_progress_goals_perc);
                progressaBarPerc_2L.setProgress(perc_2l);

                //Assists
                TextView assists_2L = findViewById(R.id.left_assists);
                int assists_2l = teams.get(0).getPeriods(1).getASSIST();
                assists_2L.setText(String.valueOf(assists_2l));
                ProgressBar progressBarAssists_2L = findViewById(R.id.assists_lpb);
                progressBarAssists_2L.setProgress(assists_2l);

                //Block
                TextView blocks_2L = findViewById(R.id.left_blocks);
                int blocks_2l = teams.get(0).getPeriods(1).getBLOCK();
                blocks_2L.setText(String.valueOf(blocks_2l));
                ProgressBar progressBarBlock_2L = findViewById(R.id.blocks_lpb);
                progressBarBlock_2L.setProgress(blocks_2l);

                //Foul
                TextView fouls_2L = findViewById(R.id.left_fouls);
                int fouls_2l = teams.get(0).getPeriods(1).getASSIST();
                fouls_2L.setText(String.valueOf(fouls_2l));
                ProgressBar progressBarFouls_2L = findViewById(R.id.fouls_lpb);
                progressBarFouls_2L.setProgress(fouls_2l);

                //Mistakes
                TextView mistakes_2L = findViewById(R.id.left_mistakes);
                int mistakes_2l = teams.get(0).getPeriods(1).getMISTAKE();
                mistakes_2L.setText(String.valueOf(mistakes_2l));
                ProgressBar progressBarMistakes_2L = findViewById(R.id.mistakes_lpb);
                progressBarMistakes_2L.setProgress(mistakes_2l);

                //Rebounds
                TextView rebounds_2L = findViewById(R.id.left_rebounds);
                int rebounds_2l = teams.get(0).getPeriods(1).getREBOUND();
                rebounds_2L.setText(String.valueOf(rebounds_2l));
                ProgressBar progressBarRebouds_2L = findViewById(R.id.rebounds_lpb);
                progressBarRebouds_2L.setProgress(rebounds_2l);

                //Steals
                TextView steals_2L = findViewById(R.id.left_steals);
                int steals_2l = teams.get(0).getPeriods(1).getSTEAL();
                steals_2L.setText(String.valueOf(steals_2l));
                ProgressBar progressBarSteals_2L = findViewById(R.id.steals_lpb);
                progressBarSteals_2L.setProgress(steals_2l);


                //Team 2
                //3
                TextView text3INR_2 = findViewById(R.id.right_three_goals_made);
                int value3inr_2= teams.get(1).getPeriods(1).getSHOT_3_IN();
                text3INR_2.setText( String.valueOf(value3inr_2) );
                ProgressBar progressBar3INR_2 = findViewById(R.id.right_progress_three_goals_made);
                progressBar3INR_2.setProgress(value3inr_2);

                int value3outr_2= teams.get(1).getPeriods(1).getSHOT_3_OUT();

                int sum3ATTR_2=value3outr_2+value3inr_2;

                TextView text3AttR_2 = findViewById(R.id.right_three_goals_attem);
                text3AttR_2.setText(String.valueOf(sum3ATTR_2));
                ProgressBar progressBar3ATTR_2 = findViewById(R.id.right_progress_three_goals_attempted);
                progressBar3ATTR_2.setProgress(sum3ATTR_2);

                //2
                TextView text2INR_2 = findViewById(R.id.right_two_point_made);
                int value2inr_2= teams.get(1).getPeriods(1).getSHOT_2_IN();
                text2INR_2.setText( String.valueOf(value2inr_2) );
                ProgressBar progressBar2INR_2 = findViewById(R.id.right_progress_two_points_made);
                progressBar2INR_2.setProgress(value2inr_2);

                int value2outr_2= teams.get(1).getPeriods(1).getSHOT_2_OUT();
                int sum2ATTR_2=value2outr_2+value2inr_2;

                TextView text2AttR_2 = findViewById(R.id.right_two_point_attempted);
                text2AttR_2.setText(String.valueOf(sum2ATTR_2));
                ProgressBar progressBar2ATTR_2 = findViewById(R.id.right_progress_two_points_attempted);
                progressBar2ATTR_2.setProgress(sum2ATTR_2);

                //1
                TextView text1INR_2 = findViewById(R.id.right_free_throws_made);
                int value1inr_2= teams.get(1).getPeriods(1).getSHOT_1_IN();
                text1INR_2.setText( String.valueOf(value1inr_2) );
                ProgressBar progressBar1INR_2 = findViewById(R.id.right_progress_free_throws_made);
                progressBar1INR_2.setProgress(value1inr_2);

                int value1outr_2= teams.get(1).getPeriods(1).getSHOT_1_OUT();
                int sum1ATTR_2=value1outr_2+value1inr_2;

                TextView text1AttR_2 = findViewById(R.id.right_free_throws_attempted);
                text1AttR_2.setText(String.valueOf(sum1ATTR_2));
                ProgressBar progressBarATTR_2 = findViewById(R.id.right_progress_free_throws_attempted);
                progressBarATTR_2.setProgress(sum1ATTR_2);

                //Score
                scoreRight_2 = 3*value3inr_2+ 2*value2inr_2 + value1inr_2 + scoreRight_1;
                TextView rightScore = findViewById(R.id.rightScore);
                rightScore.setText(String.valueOf(scoreRight_2));

                //Field
                int sumGoalsAttR_2 = sum3ATTR_2+sum2ATTR_2+sum1ATTR_2;
                TextView textATT_2R = findViewById(R.id.right_goals_attempted);
                textATT_2R.setText( String.valueOf(sumGoalsAttR_2));
                ProgressBar progressBarAtt_2R = findViewById(R.id.right_progress_goals_attempted);
                progressBarAtt_2R.setProgress(sumGoalsAttR_2);

                int sumGoalsMadeR_2 = value3inr_2+value2inr_2+value1inr_2;
                TextView textMADE_2R = findViewById(R.id.right_goals_made);
                textMADE_2R.setText( String.valueOf(sumGoalsMadeR_2));
                ProgressBar progressaBarMade_2R = findViewById(R.id.right_progress_goals_made);
                progressaBarMade_2R.setProgress(sumGoalsMadeR_2);

                TextView perc_2R = findViewById(R.id.right_goals_perc);
                int perc_2r = perc(sumGoalsAttR_2,sumGoalsMadeR_2);
                perc_2R.setText(String.valueOf(perc_2r));
                ProgressBar progressaBarPerc_2R = findViewById(R.id.right_progress_goals_perc);
                progressaBarPerc_2R.setProgress(perc_2r);

                //Assists
                TextView assists_2R = findViewById(R.id.right_assists);
                int assists_2r = teams.get(1).getPeriods(1).getASSIST();
                assists_2R.setText(String.valueOf(assists_2r));
                ProgressBar progressBarAssists_2R = findViewById(R.id.assists_rpb);
                progressBarAssists_2R.setProgress(assists_2r);

                //Block
                TextView blocks_2R = findViewById(R.id.right_blocks);
                int blocks_2r = teams.get(1).getPeriods(1).getBLOCK();
                blocks_2R.setText(String.valueOf(blocks_2r));
                ProgressBar progressBarBlock_2R = findViewById(R.id.blocks_rpb);
                progressBarBlock_2R.setProgress(blocks_2r);

                //Foul
                TextView fouls_2R = findViewById(R.id.right_fouls);
                int fouls_2r = teams.get(1).getPeriods(1).getASSIST();
                fouls_2R.setText(String.valueOf(fouls_2r));
                ProgressBar progressBarFouls_2R = findViewById(R.id.fouls_rpb);
                progressBarFouls_2R.setProgress(fouls_2r);

                //Mistakes
                TextView mistakes_2R = findViewById(R.id.right_mistakes);
                int mistakes_2r = teams.get(1).getPeriods(1).getMISTAKE();
                mistakes_2R.setText(String.valueOf(mistakes_2r));
                ProgressBar progressBarMistakes_1R = findViewById(R.id.mistakes_rpb);
                progressBarMistakes_1R.setProgress(mistakes_2r);

                //Rebounds
                TextView rebounds_2R = findViewById(R.id.rigth_rebounds);
                int rebounds_2r = teams.get(1).getPeriods(1).getREBOUND();
                rebounds_2R.setText(String.valueOf(rebounds_2r));
                ProgressBar progressBarRebouds_2R = findViewById(R.id.rebounds_rpb);
                progressBarRebouds_2R.setProgress(rebounds_2r);

                //Steals
                TextView steals_1R = findViewById(R.id.right_steals);
                int steals_1r = teams.get(1).getPeriods(0).getSTEAL();
                steals_1R.setText(String.valueOf(steals_1r));
                ProgressBar progressBarSteals_1R = findViewById(R.id.steals_rpb);
                progressBarSteals_1R.setProgress(steals_1r);

            }
        });


    }//main
    public int perc(int att,int made)
    {
        try {
            return (att / made * 100);
        }
        catch(Exception e) {
            return 0;
        }

    }
}

