package com.example.swoosh;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.graphics.Typeface;

import java.util.Random;


public class Play extends Activity {

    private int limit_from = 1;
    private int limit_to = 10;
    private int entered_value = 10;
    private int random_number = 0;
    private int current_turn = 0;
    private int turns = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Typeface font = Typeface.createFromAsset(getAssets(),
                "fonts/Baloo-Regular.ttf");

        EditText et = (EditText) findViewById(R.id.numberLevelField);
        EditText et2 = (EditText) findViewById(R.id.guessInputField);

        et.setTypeface(font);
        et2.setTypeface(font);

        updateFields();
    }

    private void updateFields()
    {
        String turn_string = "Turn:  " + Integer.toString(current_turn);
        TextView turnField = findViewById(R.id.turnField);
        turnField.setText(turn_string);

    }

    private void calculateTurns() {
        turns--;
        String result_string = "Remaining turns: " + Integer.toString(turns);
        TextView resultField = findViewById(R.id.remainingTurnField);
        resultField.setText(result_string);
    }

    public void onGuessClick(View view)
    {
        EditText inputField = findViewById(R.id.guessInputField);
        String inputString = inputField.getText().toString();
        entered_value = Integer.parseInt(inputString);


        current_turn++;

        if(entered_value > random_number) {

            String guess_string = "You guessed: " + Integer.toString(entered_value) + ", number is lower";
            TextView guessField = findViewById(R.id.guessField);
            guessField.setText(guess_string);

        }else if(entered_value < random_number){

            String guess_string = "You guessed: " + Integer.toString(entered_value) + ", number is higher";
            TextView guessField = findViewById(R.id.guessField);
            guessField.setText(guess_string);

        }else if(entered_value == random_number) {
            Intent intent = new Intent(this, win.class);
            Bundle bundle = new Bundle();
            bundle.putInt("ejimas", current_turn);

            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        calculateTurns();
        updateFields();
        if(turns < 1) {
            Intent intent = new Intent(this, lost.class);
            startActivity(intent);
        }
    }

    public void selectLimit(View view)
    {

        EditText inputField = findViewById(R.id.numberLevelField);
        String inputString = inputField.getText().toString();
        entered_value = Integer.parseInt(inputString);

        Random ran = new Random();
        random_number = ran.nextInt(entered_value)  + 1;

        TextView resultField = findViewById(R.id.msgField);
        resultField.setText("Guess the number");

        inputField.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }

}
