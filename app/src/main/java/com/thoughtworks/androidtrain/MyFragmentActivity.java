package com.thoughtworks.androidtrain;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.thoughtworks.androidtrain.fragment.AndroidTextFragment;
import com.thoughtworks.androidtrain.fragment.JavaTextFragment;

public class MyFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_fragment_layout);

        replaceFragment(new AndroidTextFragment(), null);

        findViewById(R.id.java_button).setOnClickListener(v -> {
            replaceFragment(new JavaTextFragment(), "java_text");
        });

        findViewById(R.id.android_button).setOnClickListener(v -> {
            replaceFragment(new AndroidTextFragment(), "android_text");
        });

    }

    private void replaceFragment(Fragment fragment, @Nullable String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.rightFragmentContainer, fragment, tag);
        if (tag != null) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
}