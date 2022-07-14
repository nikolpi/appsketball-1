package com.example.appsketballandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class LiveGameActivity extends AppCompatActivity {

    ListView listView;
    MyAdapter myAdapter;
    Thread refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_game);

        listView =(ListView) findViewById(R.id.list_view);

        refresh = new Thread() {
            List<Event> events;

            @Override
            public void run() {
                OkHttpHandler okHttpHandler = new OkHttpHandler();
                try {
                    while (!isInterrupted()) {
                        System.out.println("Hello from thread in fragment: " + currentThread());
                        String url = "http://" + dbConnection.IP + "/PHP/appsketballPHP/APIs/GameEventsRetrievalAPI.php?game=" + "ARIS-PAOK"/*CURRENT GAME*/ + "&id=" + -1;
                        try {
                            events = okHttpHandler.eventsRetrieval(url);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(!events.isEmpty()){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String url = "http://" + dbConnection.IP + "/PHP/appsketballPHP/APIs/GameEventsRetrievalAPI.php?game=" + "ARIS-PAOK"/*CURRENT GAME*/ + "&id=" + -1;

                                    OkHttpHandler okHttpHandler = new OkHttpHandler();

                                    try {
                                        events = okHttpHandler.eventsRetrieval(url);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    myAdapter = new MyAdapter(LiveGameActivity.this, events);
                                    listView.setAdapter(myAdapter);

                                }
                            });

                        }
                        Thread.sleep(5000);
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        refresh.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        refresh.interrupt();
    }
}
