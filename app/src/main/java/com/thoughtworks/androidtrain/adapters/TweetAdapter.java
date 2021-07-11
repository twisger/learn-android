package com.thoughtworks.androidtrain.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thoughtworks.androidtrain.R;
import com.thoughtworks.androidtrain.RecyclerViewActivity;
import com.thoughtworks.androidtrain.data.model.Tweet;

import java.util.ArrayList;
import java.util.List;


public class TweetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Tweet> tweets = new ArrayList<>();
    public static final int TweetItemType = 0;
    public static final int EndTipType = 1;

    private Activity context;
    public TweetAdapter(RecyclerViewActivity recyclerViewActivity) {
        context = recyclerViewActivity;
    }

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
                String url = tweets.get(position).getSender().getAvatar();
//                String url = "https://github.githubassets.com/images/modules/logos_page/Octocat.png";
                Log.d("TAG", "onBindViewHolder: " + url);
                Glide.with(context).load(url).into(tweetHolder.imageView);
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
        ImageView imageView = null;
        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.avatar);
            nick =  view.findViewById(R.id.nick);
            content = view.findViewById(R.id.content);
        }
    }
}
