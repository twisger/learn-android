package com.thoughtworks.androidtrain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    private Button button;
    private Disposable disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_java_activity_layout);
        findViewById(R.id.rx_java_start_button).setOnClickListener(v -> {
            button = (Button) v;
            initData();
        });
    }

    public void initData() {
        button.setEnabled(false);

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            int i = 0;
            while (i < 10) {
                i++;
                emitter.onNext(Integer.toString(i));
                SystemClock.sleep(1000);
            }
            emitter.onComplete();
        }).map(s -> getString(R.string.the_number_is) + s).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
             @Override
             public void onSubscribe(@NonNull Disposable d) {
                 disposable = d;
             }

             @Override
             public void onNext(@NonNull String s) {
                button.setText(s);
             }

             @Override
             public void onError(@NonNull Throwable e) {

             }

             @Override
             public void onComplete() {
                button.setEnabled(true);
                button.setText(getString(R.string.start));
             }
         });
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}