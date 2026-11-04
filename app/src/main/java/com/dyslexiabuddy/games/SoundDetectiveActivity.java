package com.dyslexiabuddy.games;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.dyslexiabuddy.R;
import java.util.Locale;

public class SoundDetectiveActivity extends AppCompatActivity {

    private TextToSpeech tts;
    private TextView tvScore, tvOption1, tvOption2, tvOption3;
    private MaterialCardView cardOption1, cardOption2, cardOption3;
    private int score = 0;
    private String targetWord = "Apple";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_detective);

        tvScore = findViewById(R.id.tvScore);
        tvOption1 = findViewById(R.id.tvOption1);
        tvOption2 = findViewById(R.id.tvOption2);
        tvOption3 = findViewById(R.id.tvOption3);
        
        cardOption1 = findViewById(R.id.cardOption1);
        cardOption2 = findViewById(R.id.cardOption2);
        cardOption3 = findViewById(R.id.cardOption3);

        ImageButton btnBack = findViewById(R.id.btnBack);
        FloatingActionButton btnReplayAudio = findViewById(R.id.btnReplayAudio);

        btnBack.setOnClickListener(v -> finish());
        
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
                loadNewQuestion();
            }
        });

        btnReplayAudio.setOnClickListener(v -> playSoundPrompt());

        cardOption1.setOnClickListener(v -> checkAnswer(tvOption1.getText().toString()));
        cardOption2.setOnClickListener(v -> checkAnswer(tvOption2.getText().toString()));
        cardOption3.setOnClickListener(v -> checkAnswer(tvOption3.getText().toString()));
    }

    private void loadNewQuestion() {
        tvOption1.setText("Apple 🍎");
        tvOption2.setText("Ball ⚽");
        tvOption3.setText("Cat 🐱");
        targetWord = "Apple 🍎";
        playSoundPrompt();
    }

    private void playSoundPrompt() {
        if (tts != null) {
            tts.speak("Find the word starting with A", TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void checkAnswer(String selected) {
        if (selected.equals(targetWord)) {
            score += 10;
            tvScore.setText("⭐ " + score);
            Toast.makeText(this, "Wonderful! Correct match! ⭐", Toast.LENGTH_SHORT).show();
            if (tts != null) tts.speak("Correct! Great job!", TextToSpeech.QUEUE_FLUSH, null, null);
            loadNewQuestion();
        } else {
            Toast.makeText(this, "Good try! Let's try again.", Toast.LENGTH_SHORT).show();
            if (tts != null) tts.speak("Try again!", TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
