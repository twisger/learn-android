package com.thoughtworks.androidtrain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_activity_layout);
        findViewById(R.id.thread_button).setOnClickListener(v -> {
            onClick(v);
        });
    }

    public void onClick(View v) {
        Button button = (Button) v;
        button.setEnabled(false);
        new Thread(new Runnable() {
            int count = 0;
            public void run() {
                while (count <= 10) {
                    SystemClock.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            button.setText(Integer.toString(count));
                        }
                    });
                    count++;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        button.setEnabled(true);
                        button.setText("start");
                    }
                });
            }
        }).start();
    }
}