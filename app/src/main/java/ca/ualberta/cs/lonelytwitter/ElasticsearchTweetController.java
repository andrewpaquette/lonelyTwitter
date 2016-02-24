package ca.ualberta.cs.lonelytwitter;

import android.os.AsyncTask;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by esports on 2/17/16.
 */
public class ElasticsearchTweetController {
    private static JestDroidClient client;

//    //TODO: A function that gets tweets
//    public static ArrayList<Tweet> getTweets() {
//        verifyClient();
//    }

    public static class GetTweetsTask extends AsyncTask<String, Void, ArrayList<Tweet>> {

        @Override
        protected ArrayList<Tweet> doInBackground(String... searchStrings) {
            verifyClient();

            // start our initial array list
            ArrayList<Tweet> tweets = new ArrayList<Tweet>();

            // assuming only the first search term will be used
            Search search = new Search.Builder(searchStrings[0]).addIndex("testing").addType("tweet").build();

            try {
                SearchResult results = client.execute(search);
                if (results.isSucceeded()) {
                    List<NormalTweet> returnedTweets = results.getSourceAsObjectList(NormalTweet.class);
                    tweets.addAll(returnedTweets);
                } else {
                    // TODO: add an error
                    // todo: this will trigger if our search fails
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tweets;
        }
    }

    // something similar to this in actual project regarding connection reestablished sequencing
    public static class AddTweetTask extends AsyncTask<NormalTweet, Void, Void> {
        @Override
        protected Void doInBackground(NormalTweet... tweets) {
            verifyClient();
            for (int i = 0; i < tweets.length; i++) {
                NormalTweet tweet = tweets[i];
                Index index = new Index.Builder(tweet).index("testing").type("tweet").build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        // set the id to tweet listed by es
                        tweet.setId(result.getId());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public static class SearchTweetsTask extends AsyncTask<String, Void, ArrayList<Tweet>> {

        @Override
        protected ArrayList<Tweet> doInBackground(String... searchStrings) {
            verifyClient();
            ArrayList<Tweet> tweets = new ArrayList<Tweet>();
            String query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"filtered\" : {\n" +
                    "            \"query\" : {\n" +
                    "                \"query_string\" : {\n" +
                    "                    \"query\" : " + searchStrings[0] + "\n" +
                    "                }\n" +
                    "            },\n" +
                    "            \"filter\" : {\n" +
                    "                \"term\" : { \"user\" : \"kimchy\" }\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";

            Search search = new Search.Builder(query)
                    // multiple index or types can be added.
                    .addIndex("twitter")
                    .addIndex("tweet")
                    .build();

            try {
                SearchResult results = client.execute(search);
                if (results.isSucceeded()) {
                    List<NormalTweet> returnedTweets = results.getSourceAsObjectList(NormalTweet.class);
                    tweets.addAll(returnedTweets);
                } else {
                    // TODO: add an error
                    // todo: this will trigger if our search fails
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tweets;
        }
    }

    // using a builder design pattern
    // verify that 'client' exists
    public static void verifyClient() {
        if(client == null) {
            // TODO: put url somewhere else
            DroidClientConfig.Builder builder =
                    new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}
