package com.dyslexiabuddy.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;
import com.dyslexiabuddy.R;

public class LanguageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        MaterialCardView cardEnglish = findViewById(R.id.cardEnglish);
        MaterialCardView cardHindi = findViewById(R.id.cardHindi);
        MaterialCardView cardKannada = findViewById(R.id.cardKannada);

        android.view.View.OnClickListener proceedListener = v -> {
            Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        };

        cardEnglish.setOnClickListener(proceedListener);
        cardHindi.setOnClickListener(proceedListener);
        cardKannada.setOnClickListener(proceedListener);
    }
}
