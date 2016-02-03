package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by apaquett on 1/26/16.
 */
public class TweetList {

    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();





    // LAB EXERCISE NUMBER FOUR: fix these with tdd
    public void add(Tweet tweet) {
        tweets.add(tweet);
    }

    public boolean hasTweet(Tweet tweet){
        return tweets.contains(tweet);
    }

    public TweetList getTweets() {
        // placeholder
        Tweet test = tweets.get(index);
        return test;
    }

    public void removeTweet(Tweet tweet) {

    }

    public int getCount() {
        return (5);
    }
}
