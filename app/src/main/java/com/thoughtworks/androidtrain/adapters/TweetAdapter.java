package com.thoughtworks.androidtrain.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.androidtrain.R;
import com.thoughtworks.androidtrain.data.model.Tweet;

import java.util.ArrayList;
import java.util.List;


public class TweetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Tweet> tweets = new ArrayList<>();
    public static final int TweetItemType = 0;
    public static final int EndTipType = 1;
    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TweetItemType:
                return new ViewHolder(from.inflate(R.layout.tweet_item_layout, parent, false));
            case EndTipType:
                return new ViewHolder(from.inflate(R.layout.tweet_end_tip_layout, parent, false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return EndTipType;
        }
        return TweetItemType;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TweetItemType:
                ViewHolder tweetHolder = (ViewHolder)(holder);
                tweetHolder.nick.setText(tweets.get(position).getSender().getNick());
                tweetHolder.content.setText(tweets.get(position).getContent());
            case EndTipType:
        }

    }

    @Override
    public int getItemCount() {
        return tweets.size() + 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nick = null;
        TextView content = null;
        public ViewHolder(View view) {
            super(view);
            nick =  view.findViewById(R.id.nick);
            content = view.findViewById(R.id.content);
        }
    }
}
