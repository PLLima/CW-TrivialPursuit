package com.example.cw_trivialpursuit;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collections;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private LinearLayout cardLayout;
    private int cardCount;
    private int cardIndex;
    private int tryCount;
    private int userScore;
    private int maxScore;
    private Vector<Card> cardSet;
    private TextView questionText;
    private Vector<Button> buttons;
    private Vector<String> propositions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ViewGroup.LayoutParams layoutParams;
        Quiz quiz;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setContentView(R.layout.activity_main);
        cardLayout = findViewById(R.id.card);

        quiz = new Quiz();
        cardCount = quiz.getCardCount();
        cardIndex = 0;
        cardSet = new Vector<>(quiz.getCards());
        Collections.shuffle(cardSet);

        tryCount = 0;
        userScore = 0;
        maxScore = 2 * cardCount;

        questionText = findViewById(R.id.question);
        questionText.setText(cardSet.get(cardIndex).getQuestion());

        propositions = new Vector<>(cardSet.get(cardIndex).getWrongAnswers());
        propositions.add(cardSet.get(cardIndex).getRightAnswer());
        Collections.shuffle(propositions);

        buttons = new Vector<>();
        int answerCount = propositions.toArray().length;
        for(int i = 0; i < answerCount; i++) {
            buttons.add(new Button(this));
            cardLayout.addView(buttons.get(i));
            layoutParams = buttons.get(i).getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            buttons.get(i).setLayoutParams(layoutParams);
            buttons.get(i).setText(propositions.get(i));
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast t = new Toast(getApplicationContext());
                    t.setDuration(Toast.LENGTH_SHORT);

                    Button button = (Button) view;
                    String buttonText = button.getText().toString();
                    if(buttonText.equals(cardSet.get(cardIndex).getRightAnswer())) {
                        t.setText("Right answer!");
                        t.show();

                        int lastAnswerCount = propositions.toArray().length;
                        for(int i = 0; i < lastAnswerCount; i++)
                            cardLayout.removeView(buttons.get(i));

                        cardIndex++;
                        if(cardIndex == cardCount)
                            cardIndex = 0;

                        questionText.setText(cardSet.get(cardIndex).getQuestion());
                        propositions = new Vector<>(cardSet.get(cardIndex).getWrongAnswers());
                        propositions.add(cardSet.get(cardIndex).getRightAnswer());
                        Collections.shuffle(propositions);

                        buttons = new Vector<>();
                        int answerCount = propositions.toArray().length;
                        for(int i = 0; i < answerCount; i++) {
                            buttons.add(new Button(getApplicationContext()));
                            cardLayout.addView(buttons.get(i));
                            ViewGroup.LayoutParams layoutParams = buttons.get(i).getLayoutParams();
                            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                            buttons.get(i).setLayoutParams(layoutParams);
                            buttons.get(i).setText(propositions.get(i));
                            buttons.get(i).setOnClickListener(this);
                        }
                    } else {
                        t.setText("Wrong answer! Try again...");
                        t.show();
                    }
                }
            });
        }
    }

}