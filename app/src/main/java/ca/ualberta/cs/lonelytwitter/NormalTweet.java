package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by romansky on 1/12/16.
 */

/**
 * NormalTweet is the most commonly used object managed by LonelyTwitter.
 * It stores text and a date and has some basic get methods.
 * And a boolean state called isImportant.
 * @see NormalTweet
 * @author Andrew Paquette, but like not rly
 * @version 1.57, 12/19/03
 * @since 2016-02-02
 */

public class NormalTweet extends Tweet implements Tweetable {
    public NormalTweet(Date date, String message) {
        super(date, message);
    }

    public NormalTweet(String message) {
        super(message);
    }

    public Date getDate() {
        return this.date;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public Boolean isImportant() {
        return Boolean.FALSE;
    }
}
