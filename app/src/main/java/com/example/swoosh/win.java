package com.example.swoosh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class win extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Bundle bundle;

        bundle = getIntent().getExtras();
        int ejimas = bundle.getInt("ejimas");

        Database dbhandler = new Database(this);
        SharedPreferences sharedPref = getSharedPreferences("Nustatymai", Context.MODE_PRIVATE);
        String name = sharedPref.getString("name", "");

        dbhandler.addEntry(new DatabaseEntry(0, name,  ejimas));
    }

    public void goBack (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
