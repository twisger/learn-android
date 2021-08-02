package com.thoughtworks.androidtrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isKnown = getIsKnown();
        if (isKnown) {
            initKnownUI();
        } else {
            initUnknownUI();
        }
    }
    public void initUnknownUI() {
        setContentView(R.layout.sp_activity_layout);
        findViewById(R.id.i_know).setOnClickListener(v -> {
            setIsKnown(true);
            initKnownUI();
        });

    }
    public void initKnownUI() {
        setContentView(R.layout.sp_activity_layout_known);
    }
    public void setIsKnown(boolean isKnown) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.is_known_key), isKnown);
        editor.apply();
    }
    public Boolean getIsKnown() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        boolean isKnown = sharedPref.getBoolean(getString(R.string.is_known_key), false);
        return isKnown;
    }
}