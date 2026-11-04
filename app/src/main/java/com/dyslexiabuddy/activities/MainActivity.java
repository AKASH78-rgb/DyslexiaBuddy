package com.dyslexiabuddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;
import com.dyslexiabuddy.R;
import com.dyslexiabuddy.games.SoundDetectiveActivity;
import com.dyslexiabuddy.games.LetterMatchActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialCardView cardAlphabet = findViewById(R.id.cardAlphabet);
        MaterialCardView cardPhonics = findViewById(R.id.cardPhonics);
        MaterialCardView cardTracing = findViewById(R.id.cardTracing);
        MaterialCardView cardGames = findViewById(R.id.cardGames);
        Button btnSettings = findViewById(R.id.btnSettings);

        cardAlphabet.setOnClickListener(v -> Toast.makeText(this, "Opening Alphabet Learning...", Toast.LENGTH_SHORT).show());
        cardPhonics.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SoundDetectiveActivity.class));
        });
        cardTracing.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TracingActivity.class));
        });
        cardGames.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LetterMatchActivity.class));
        });
        btnSettings.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
    }
}
