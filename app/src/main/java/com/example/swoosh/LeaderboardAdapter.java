package com.example.swoosh;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LeaderboardAdapter extends BaseAdapter {

    ArrayList<DatabaseEntry> dataList;
    Activity activity;

    LeaderboardAdapter(ArrayList<DatabaseEntry> d, Activity a)
    {
        dataList = d;
        activity = a;
    }

    public int getCount()
    {
        if (dataList != null)
        {
            return dataList.size();
        }
        return 0;
    }

    public long getItemId(int position) { return position; }

    public Object getItem(int position)
    {
        if(dataList != null) {
            return dataList.get(position);
        }
        return null;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        View vi = convertView;
        if(vi == null)
        {
            LayoutInflater li = LayoutInflater.from(activity);
            vi = li.inflate(R.layout.leaderboard_layout, null);
        }

        TextView nameText = (TextView)vi.findViewById(R.id.listview_name);
        TextView scoreText = (TextView)vi.findViewById(R.id.listview_score);

        DatabaseEntry le = dataList.get(position);

        nameText.setText(le.getName());
        scoreText.setText(Integer.toString(le.getScore()));

        return vi;
    }
}
