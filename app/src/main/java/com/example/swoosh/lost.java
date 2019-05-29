package com.example.swoosh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class lost extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);
    }

    public void playAgain (View view){
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }
}
