package com.example.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Chronometer stopwatch;
    private long pauseOffset;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopwatch = findViewById(R.id.stopwatch);
        stopwatch.setFormat("Time: %s");
        stopwatch.setBase(SystemClock.elapsedRealtime());

        stopwatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer stopwatch) {
                if ((SystemClock.elapsedRealtime() - stopwatch.getBase()) >= 1000000) {
                    stopwatch.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(MainActivity.this, "Bing!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void startWatch(View v) {
        if (!running) {
            stopwatch.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            stopwatch.start();
            running = true;
        }
    }

    public void pauseWatch(View v) {
        if (running) {
            stopwatch.stop();
            pauseOffset = SystemClock.elapsedRealtime() - stopwatch.getBase();
            running = false;
        }
    }

    public void resetWatch(View v) {
        stopwatch.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
}