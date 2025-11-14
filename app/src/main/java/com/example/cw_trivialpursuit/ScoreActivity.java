package com.example.cw_trivialpursuit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScoreActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private float finalScore;
    private final String USERNAME_KEY = "username";
    private final String SCORE_KEY = "score";
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_score);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scoreScreen), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView scoreText = findViewById(R.id.scoreText);
        TextView scoreGuideText = findViewById(R.id.scoreGuideText);
        TextView newUsernameText = findViewById(R.id.usernameTitle);
        EditText userInput = findViewById(R.id.usernameInput);
        Button submitUserButton = findViewById(R.id.submitUser);
        submitUserButton.setVisibility(View.GONE);
        Intent lastActivity = getIntent();

        int userScore = lastActivity.getIntExtra("us", 0);
        int maxScore = lastActivity.getIntExtra("ms", 0);
        finalScore = (float) userScore / maxScore;
        String scoreInfo = "You got " + userScore + " over " + maxScore + " questions correct.\n" + "Final score: " + finalScore + " points.";
        scoreText.setText(scoreInfo);

        userInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                username = s.toString();
                if(!s.toString().isEmpty())
                    submitUserButton.setVisibility(View.VISIBLE);
                else
                    submitUserButton.setVisibility(View.GONE);
            }
        });

        sharedPreferences = getSharedPreferences("high-score", Context.MODE_PRIVATE);
        if(!sharedPreferences.contains(USERNAME_KEY)) {
            String scoreGuideInfo = "You established the first record! Please, register your username below.";
            scoreGuideText.setText(scoreGuideInfo);
        } else {
            String storedUsername = sharedPreferences.getString(USERNAME_KEY, "Username");
            float storedScore = sharedPreferences.getFloat(SCORE_KEY, 0);
            if(finalScore > storedScore) {
                String scoreGuideInfo = "You bet the previous record of " + storedScore + " points reached by " + storedUsername + ". \nPlease register your username below.";
                scoreGuideText.setText(scoreGuideInfo);
            } else {
                newUsernameText.setVisibility(View.GONE);
                userInput.setVisibility(View.GONE);
                String scoreGuideInfo = "Your score was lower than the record of " + storedScore + " points reached by " + storedUsername + ". \nPlease try again.";
                scoreGuideText.setText(scoreGuideInfo);
            }
        }
    }

    public void handleClick(View view) {
        Toast.makeText(this, "Username successfully registered", Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME_KEY, username);
        editor.putFloat(SCORE_KEY, finalScore);
        editor.apply();
    }
}