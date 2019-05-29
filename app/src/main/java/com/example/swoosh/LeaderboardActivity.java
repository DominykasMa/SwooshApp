package com.example.swoosh;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;


public class LeaderboardActivity extends Activity {

    ArrayList<DatabaseEntry> mContents;

    LeaderboardAdapter mAdapter;

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        mContents = prepareContent();

        String[] from = new String[] { "name", "score"};
        int[] to = new int[] {R.id.listview_name, R.id.listview_score};

        mAdapter = new LeaderboardAdapter(mContents, this);

        mListView = (ListView)findViewById(R.id.leaderboard_listview);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) findViewById(R.id.leaderboard_header);
                textView.setText(parent.getItemAtPosition(position).toString());
            }
        });


    }

    private ArrayList prepareContent()
    {
        mContents = new ArrayList<>();

        Database dbhandler = new Database(this);

        ArrayList<DatabaseEntry> entries = dbhandler.getAllEntries();
        return entries;
    }

    class WebTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            StringBuilder builder = new StringBuilder();
            if(params.length > 0) {
                try {
                    URL url = new URL(params[0]);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    InputStream in = con.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line = null;
                    while((line = reader.readLine()) != null)
                    {
                        builder.append(line);
                    }
                    con.disconnect();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return builder.toString();

        }

        @Override
        protected void onPostExecute(String s)
        {
            parseJson(s);
        }
    }

    public void parseJson(String json)
    {
        try {
            //JSONObject object = new JSONObject(json);
            JSONArray array = new JSONArray(json);
            if(mContents != null) {
                mContents.clear();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    DatabaseEntry entry = new DatabaseEntry(i, obj.getString("name"), obj.getInt("score"));
                    mContents.add(entry);
                }
                mAdapter.notifyDataSetChanged();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
