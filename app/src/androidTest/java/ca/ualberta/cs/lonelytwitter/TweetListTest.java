package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by apaquett on 1/26/16.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {

    public TweetListTest() {
        super(LonelyTwitterActivity.class);
    }

    public void testAddTweet() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("test");

        tweets.add(tweet);

        assertTrue(tweets.hasTweet(tweet));
    }

    public void testGetTweet() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("test");

        tweets.add(tweet);
        Tweet returnedTweet = tweets.getTweets(0);

        assertEquals(returnedTweet.getMessage(), tweet.getMessage());
        assertEquals(returnedTweet.getDate(), tweet.getDate());
    }

    public void testHasTweet() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("test");

        assertFalse(tweets.hasTweet(tweet));

        tweets.add(tweet);
        tweets.hasTweet(tweet);

        assertTrue(tweets.hasTweet(tweet));
    }

    public void testRemoveTweet() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("test");

        tweets.add(tweet);
        assertTrue(tweets.hasTweet(tweet));

        tweets.removeTweet(tweet);
        assertFalse(tweets.hasTweet(tweet));
    }

    public void testGetCount() {

        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("test");

        tweets.add(tweet);
        int test =
    }

}
