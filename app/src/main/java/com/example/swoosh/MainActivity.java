package com.example.swoosh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPlayClick (View view){
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }

    public void onSettingsClick (View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void onAboutClick (View view){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void onLeaderboardClick (View view){
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }
}
