package com.example.appsketballandroid;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsketballandroid.livestatistics.LiveStatisticsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LiveGameAndStatisticsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiveGameAndStatisticsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LiveGameAndStatisticsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LiveGameAndStatisticsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LiveGameAndStatisticsFragment newInstance(String param1, String param2) {
        LiveGameAndStatisticsFragment fragment = new LiveGameAndStatisticsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<Event> events = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_live_game_and_statistics, container, false);
        TextView game = v.findViewById(R.id.liveGameTextView);

        String url = "http://" + dbConnection.IP + "/PHP/appsketballPHP/APIs/GameEventsRetrievalAPI.php?game=" + "ARIS-PAOK"/*CURRENT GAME*/ + "&id=" + -1;
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        try {
            events = okHttpHandler.eventsRetrieval(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView homeTeam = v.findViewById(R.id.homeTextView);
        TextView awayTeam = v.findViewById(R.id.awayTextView);
        ImageView homeImageView = v.findViewById(R.id.homeImageView);
        ImageView awayImageView = v.findViewById(R.id.awayImageView);


        /* Here we would get the teams of the game with a service, after the user would click the
            game he wanted to watch.
         */
        String teams[]= events.get(0).getGame().split("-");

        homeTeam.setText(teams[0]);
        awayTeam.setText(teams[1]);

        String urlHomeTeam = "http://"+ dbConnection.IP +"/PHP/appsketballPHP/APIs/TeamDetailsRetrievalAPI.php?name="+ teams[0];
        String urlAwayTeam = "http://"+ dbConnection.IP +"/PHP/appsketballPHP/APIs/TeamDetailsRetrievalAPI.php?name="+ teams[1];

        try {
            Team homeTeamDetails = okHttpHandler.teamDetails(urlHomeTeam);
            Team awayTeamDetails = okHttpHandler.teamDetails(urlAwayTeam);
            Picasso.get().load(homeTeamDetails.getBadge()).into(homeImageView);
            Picasso.get().load(awayTeamDetails.getBadge()).into(awayImageView);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Thread scoreAndQuarterThread = new Thread() {
            private int scoreHome;
            private int scoreAway;
            TextView period = v.findViewById(R.id.periodTextView);
            TextView score = v.findViewById(R.id.scoreTextView);
            List<Event> events;

            @Override
            public void run() {
                int lastId = -1;
                OkHttpHandler okHttpHandler = new OkHttpHandler();
                try {
                    while (!isInterrupted()) {
                        // System.out.println("111111111Hello from thread in fragment: " + currentThread());
                        String url = "http://" + dbConnection.IP + "/PHP/appsketballPHP/APIs/GameEventsRetrievalAPI.php?game=" + "ARIS-PAOK"/*CURRENT GAME*/ + "&id=" + lastId;
                        try {
                            events = okHttpHandler.eventsRetrieval(url);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(!events.isEmpty()){
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    for(Event e: events){
                                        switch (e.getEventAction()){
                                            case "SHOT_1_IN":
                                                if(e.getTeam().equals(teams[0]))
                                                    scoreHome+=1;
                                                else
                                                    scoreAway+=1;
                                                break;
                                            case "SHOT_2_IN":
                                                if(e.getTeam().equals(teams[0]))
                                                    scoreHome+=2;
                                                else
                                                    scoreAway+=2;
                                                break;
                                            case "SHOT_3_IN":
                                                if(e.getTeam().equals(teams[0]))
                                                    scoreHome+=3;
                                                else
                                                    scoreAway+=3;
                                                break;
                                        }
                                    }
                                    score.setText(scoreHome+" - "+scoreAway);
                                    period.setText("Quarter: "+String.valueOf(events.get(events.size()-1).getQuarter()));
                                }
                            });
                            lastId = events.get(events.size()-1).getId();

                        }
                        Thread.sleep(5000);
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        scoreAndQuarterThread.start();

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LiveGameActivity.class));
                scoreAndQuarterThread.interrupt();
            }
        });
        TextView statistics = v.findViewById(R.id.liveStatisticsTextView);
        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LiveStatisticsActivity.class));
                scoreAndQuarterThread.interrupt();
            }
        });


        // Inflate the layout for this fragment
        return v;
    }


}