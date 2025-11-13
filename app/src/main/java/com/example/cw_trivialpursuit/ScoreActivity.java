package com.example.cw_trivialpursuit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScoreActivity extends AppCompatActivity {

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
        Intent lastActivity = getIntent();
        int userScore = lastActivity.getIntExtra("us", 0);
        int maxScore = lastActivity.getIntExtra("ms", 0);
        float finalScore = (float) userScore / maxScore;
        String displayText = "You got " + userScore + " points over " + maxScore + "\n" + "Final score: " + finalScore;
        scoreText.setText(displayText);
    }
}