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

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private final String USERNAME_KEY = "username";
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
        EditText userInput = findViewById(R.id.usernameInput);
        Button submitUserButton = findViewById(R.id.submitUser);
        submitUserButton.setVisibility(View.GONE);
        Intent lastActivity = getIntent();

        int userScore = lastActivity.getIntExtra("us", 0);
        int maxScore = lastActivity.getIntExtra("ms", 0);
        float finalScore = (float) userScore / maxScore;
        String scoreInfo = "You got " + userScore + " over " + maxScore + " points.\n" + "Final score: " + finalScore + ".";
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

        }
    }
    
    public void handleClick(View view) {
        Toast.makeText(this, "Username successfully registered", Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME_KEY, username);
        editor.apply();
    }
}