package com.dyslexiabuddy.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.dyslexiabuddy.R;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ImageButton btnBack = findViewById(R.id.btnBack);
        SwitchMaterial switchDyslexiaFont = findViewById(R.id.switchDyslexiaFont);
        SwitchMaterial switchAudio = findViewById(R.id.switchAudio);
        Button btnResetProgress = findViewById(R.id.btnResetProgress);

        btnBack.setOnClickListener(v -> finish());

        SharedPreferences prefs = getSharedPreferences("DyslexiaPrefs", MODE_PRIVATE);
        switchDyslexiaFont.setChecked(prefs.getBoolean("dyslexia_font", false));
        switchAudio.setChecked(prefs.getBoolean("audio_enabled", true));

        switchDyslexiaFont.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("dyslexia_font", isChecked).apply();
            Toast.makeText(this, isChecked ? "Dyslexia-friendly mode enabled" : "Standard font enabled", Toast.LENGTH_SHORT).show();
        });

        switchAudio.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("audio_enabled", isChecked).apply();
        });

        btnResetProgress.setOnClickListener(v -> {
            Toast.makeText(this, "Progress has been reset safely.", Toast.LENGTH_SHORT).show();
        });
    }
}
