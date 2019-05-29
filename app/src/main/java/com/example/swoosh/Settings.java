package com.example.swoosh;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        loadSettings();
    }
    public void loadSettings()
    {
        SharedPreferences sharedPref = getSharedPreferences("Nustatymai", Context.MODE_PRIVATE);
        String name = sharedPref.getString("name", "Enter your name");
        String email = sharedPref.getString("email", "Enter your email");
        Integer age = sharedPref.getInt("age", 0);

        EditText nameField = findViewById(R.id.Name);
        nameField.setText(name);

        EditText emailField = findViewById(R.id.Email);
        emailField.setText(email);

        EditText ageField = findViewById(R.id.Age);
        ageField.setText(age+"");
    }

    public void mySaveClick(View view)
    {
        EditText nameField = findViewById(R.id.Name);
        String name = nameField.getText().toString();

        EditText emailField = findViewById(R.id.Email);
        String email = emailField .getText().toString();

        EditText ageField = findViewById(R.id.Age);
        Integer age = Integer.parseInt(ageField.getText().toString());

        SharedPreferences.Editor sharedPreEditor = getSharedPreferences("Nustatymai", Context.MODE_PRIVATE).edit();
        sharedPreEditor.putString("name", name);
        sharedPreEditor.putString("email", email);
        sharedPreEditor.putInt("age", age);
        sharedPreEditor.apply();

        finish();

    }
}
