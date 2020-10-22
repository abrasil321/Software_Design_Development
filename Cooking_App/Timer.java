package com.example.cs246teamproject_cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Timer extends AppCompatActivity {

    private EditText mEditTextInput;
    private EditText sEditTextInput;
    private TextView countdownText;
    private Button mButtonSet;
    private Button countdownButton;
    private Button mButtonReset;
    private Button mButtonAdd;

    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long mStartTimeInMillis;
    private long timeLeftInMilliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        mEditTextInput = findViewById(R.id.edit_text_input);
        sEditTextInput = findViewById(R.id.editText_seconds);
        countdownText = findViewById(R.id.countdown_text);

        mButtonSet = findViewById(R.id.button_set);
        countdownButton = findViewById(R.id.countdown_button);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = mEditTextInput.getText().toString();
                String seconds_input = sEditTextInput.getText().toString();
                // Send a toast message if set field is empty
                if (input.length() == 0) {
                    Toast.makeText(Timer.this,
                            "Field cannot be empty!", Toast.LENGTH_SHORT).show();
                    //return;
                }


                //long millisInput = Long.parseLong(input) * 60000;
                long millisInput = (input.isEmpty()? 0 : Long.parseLong(input)) * 60000 + (seconds_input.isEmpty() ? 0 : Long.parseLong(seconds_input)) * 1000;
                // Send a toast message if set field is not a positive number
                if (millisInput == 0 ) {
                    Toast.makeText(Timer.this,
                            "Please enter a positive number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                setTimer(millisInput); // Send the set input to the setTimer function
                mEditTextInput.setText("");
            }
        });

        mButtonAdd = findViewById(R.id.button_addTime);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEditTextInput.getText().toString();
                String seconds_input = sEditTextInput.getText().toString();
                long millisInput = (input.isEmpty()? 0 : Long.parseLong(input)) * 60000 + (seconds_input.isEmpty() ? 0 : Long.parseLong(seconds_input)) * 1000;
                addTimer(millisInput); // Send the set input to the setTimer function
            }
        });

        // When clicks Start, calls startStop function
        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String text = "%02d:%02d";
                //--------------------------------------------------------------------------------
                if (countdownText.getText().toString() == text) {
                    stopTimer();
                    return;
                }
                else {
                    startStop();
                    return;
                }

            }
        });

        // When clicks Reset, calls resetTimer function
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateTimer();
    }

    // Set the time according to the user input
    public void setTimer(long milliseconds) {
        stopTimer();
        timeLeftInMilliseconds = milliseconds;
        updateTimer();
    }

    public void addTimer(long milliseconds) {
        boolean wasRunning = false;
        if (timerRunning)
            wasRunning = true;
        stopTimer();
        timeLeftInMilliseconds += milliseconds;
        updateTimer();
        if(wasRunning)
            startTimer();
    }

    // if time is running, the countdown stops; if the time is not running, countdown starts
    public void startStop() {
        if(timerRunning) {
            stopTimer();
        }
        else {
            startTimer();
        }
    }

    public void startTimer()
    {
        //Start the timer and update timer display
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateTimer();
            }

            // When time is not running, button shows START
            @Override
            public void onFinish() {
                timerRunning = false;
                countdownButton.setText("START");
            }
        }.start();

        //After time starts the Button shows "PAUSE"
        timerRunning = true;
        countdownButton.setText("PAUSE");
    }

    public void stopTimer() {

        //After stop timer Button says "start"
        countdownButton.setText("START");
        timerRunning = false;

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    // Set the time to zero
    public void resetTimer() {
        stopTimer();
        timeLeftInMilliseconds = mStartTimeInMillis;
        updateTimer();
        mEditTextInput.setText("");
        sEditTextInput.setText("");
    }

    // Updates display timer
    public void updateTimer() {
        int hours = (int) (timeLeftInMilliseconds / 1000) / 3600; // Calculates hours
        int minutes = (int) ((timeLeftInMilliseconds / 1000) % 3600) / 60; //Calculates minutes
        int seconds = (int) (timeLeftInMilliseconds / 1000) % 60; //Calculates seconds

        String timeLeftText;
        if (hours > 0) {
            timeLeftText = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftText = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
            
        }
        countdownText.setText(timeLeftText); //Displays time and updates it
    }
}
