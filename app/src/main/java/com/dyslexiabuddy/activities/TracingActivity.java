package com.dyslexiabuddy.activities;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.dyslexiabuddy.R;
import com.dyslexiabuddy.customviews.TracingCanvasView;
import java.util.Locale;

public class TracingActivity extends AppCompatActivity {

    private TracingCanvasView tracingCanvas;
    private TextView tvTracingTitle;
    private TextToSpeech tts;
    private String[] alphabet = {"A", "B", "C", "D", "E", "F", "G"};
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracing);

        tracingCanvas = findViewById(R.id.tracingCanvas);
        tvTracingTitle = findViewById(R.id.tvTracingTitle);
        ImageButton btnBack = findViewById(R.id.btnBack);
        ImageButton btnSpeak = findViewById(R.id.btnSpeak);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnNextLetter = findViewById(R.id.btnNextLetter);

        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
            }
        });

        updateCurrentLetter();

        btnBack.setOnClickListener(v -> finish());
        btnSpeak.setOnClickListener(v -> speakLetter());
        btnClear.setOnClickListener(v -> tracingCanvas.clearCanvas());
        btnNextLetter.setOnClickListener(v -> {
            if (currentIndex < alphabet.length - 1) {
                currentIndex++;
                updateCurrentLetter();
            } else {
                Toast.makeText(this, "Great Job! You completed the alphabet set! ⭐", Toast.LENGTH_LONG).show();
                currentIndex = 0;
                updateCurrentLetter();
            }
        });
    }

    private void updateCurrentLetter() {
        String letter = alphabet[currentIndex];
        tvTracingTitle.setText("Trace Letter " + letter);
        tracingCanvas.setTargetText(letter);
        speakLetter();
    }

    private void speakLetter() {
        if (tts != null) {
            tts.speak("Trace letter " + alphabet[currentIndex], TextToSpeech.QUEUE_FLUSH, null, null);
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
