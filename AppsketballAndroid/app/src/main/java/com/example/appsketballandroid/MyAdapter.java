package com.example.appsketballandroid;//package com.example.appsketballandroid;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    Context context;
    List<Event> listEvents;

    public MyAdapter(Context context, List<Event> listEvents) {
        this.context = context;
        this.listEvents = listEvents;
    }

    @Override
    public int getCount() {
        return listEvents.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        int delimiterIndex = listEvents.get(position).getGame().indexOf("-");
        if (delimiterIndex != -1) {
            String substr = listEvents.get(position).getGame().substring(0, delimiterIndex);
            if (listEvents.get(position).getTeam().equals(substr))
                view = LayoutInflater.from(context).inflate(R.layout.events_list_home, parent, false);
            else
                view = LayoutInflater.from(context).inflate(R.layout.events_list_away, parent, false);
        }
        TextView playerName = view.findViewById(R.id.playerNameTextView);
        TextView event = view.findViewById(R.id.eventTextView);
        TextView quarter = view.findViewById(R.id.quarterTextView);

        playerName.setText(listEvents.get(position).getPlayerName());
        event.setText("Event: "+listEvents.get(position).getEventAction());
        quarter.setText("Quarter: "+String.valueOf(listEvents.get(position).getQuarter()));

        return view;
    }
}
