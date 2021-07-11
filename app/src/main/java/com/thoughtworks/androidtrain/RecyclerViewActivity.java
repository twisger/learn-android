package com.thoughtworks.androidtrain;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.thoughtworks.androidtrain.data.model.Tweet;
import com.thoughtworks.androidtrain.data.json.TweetJson;
import com.thoughtworks.androidtrain.adapters.TweetAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerViewActivity extends AppCompatActivity {
    private TweetAdapter tweetAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);
        initUI();
    }

    private void initUI() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tweetAdapter = new TweetAdapter();
        recyclerView.setAdapter(tweetAdapter);
        Gson gson = new Gson();
        List<Tweet> tweetList = gson.fromJson(TweetJson.tweetJson, new TypeToken<ArrayList<Tweet>>() {}.getType());
        List<Tweet> filteredList = tweetList.stream().filter(tweet -> tweet.getError() == null && tweet.getUnknownError() == null ).collect(Collectors.toList());
        tweetAdapter.setTweets(filteredList);
    }
}