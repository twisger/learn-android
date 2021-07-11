package com.thoughtworks.androidtrain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;


public class HandlerActivity extends AppCompatActivity {
    public static final int MessageTypeA = 1;
    public static final int MessageTypeB = 2;
    private Handler childThreadHandler;
    private Thread childThread = new Thread(new Runnable() {
        @Override
        public void run() {
            Looper.prepare();
            childThreadHandler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    switch (msg.what) {
                        case MessageTypeA:
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage("MessageTypeA");
                                }
                            });
                            break;
                        case MessageTypeB:
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage("MessageTypeB");
                                }
                            });
                            break;
                    }
                    return true;
                }
            });
            Looper.loop();
        }

    });
    Toast toast = null;
    public void showMessage(String text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_activity_layout);
        findViewById(R.id.handler_button1).setOnClickListener(v -> {
            Message msg = Message.obtain();
            msg.what = MessageTypeA;
            childThreadHandler.sendMessage(msg);
        });

        findViewById(R.id.handler_button2).setOnClickListener(v -> {
            Message msg = Message.obtain();
            msg.what = MessageTypeB;
            childThreadHandler.sendMessage(msg);
        });
        childThread.start();
    }

}