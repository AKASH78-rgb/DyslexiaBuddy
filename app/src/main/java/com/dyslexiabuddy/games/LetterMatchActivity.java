package com.dyslexiabuddy.games;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;
import com.dyslexiabuddy.R;

public class LetterMatchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_match);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        TextView tvCard1 = findViewById(R.id.tvCard1);
        TextView tvCard2 = findViewById(R.id.tvCard2);
        TextView tvCard3 = findViewById(R.id.tvCard3);
        TextView tvCard4 = findViewById(R.id.tvCard4);
        TextView tvCard5 = findViewById(R.id.tvCard5);
        TextView tvCard6 = findViewById(R.id.tvCard6);

        tvCard1.setText("A");
        tvCard2.setText("a");
        tvCard3.setText("B");
        tvCard4.setText("b");
        tvCard5.setText("C");
        tvCard6.setText("c");

        MaterialCardView card1 = findViewById(R.id.card1);
        card1.setOnClickListener(v -> {
            Toast.makeText(this, "Matched A & a! ⭐", Toast.LENGTH_SHORT).show();
        });
    }
}
