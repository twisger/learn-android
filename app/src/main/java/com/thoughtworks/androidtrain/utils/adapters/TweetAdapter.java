package com.thoughtworks.androidtrain.utils.adapters;

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

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        return new ViewHolder(from.inflate(R.layout.tweet_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder tweetHolder = (ViewHolder)(holder);
        tweetHolder.nick.setText(tweets.get(position).getSender().getNick());
        tweetHolder.content.setText(tweets.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return tweets.size();
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
