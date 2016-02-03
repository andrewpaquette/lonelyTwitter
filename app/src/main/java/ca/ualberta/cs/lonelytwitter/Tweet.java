package ca.ualberta.cs.lonelytwitter;

import android.os.Message;

import java.util.Date;

/**
 * Created by romansky on 1/12/16.
 */

/**
 * Tweet is the abstract class from which Normal and Important inherit.
 * It defines some simple set of functions, mostly getters and setters.
 *
 * @see NormalTweet
 * @author Andrew Paquette, but like not rly
 * @version 1.57, 12/19/03
 * @since 2016-02-02
 */

public abstract class Tweet {
    protected Date date;
    protected String message;

    public abstract Boolean isImportant();

    public Tweet(Date date, String message) {
        this.date = date;
        this.message = message;
    }

    public Tweet(String message) {
        this.message = message;
        this.date = new Date();
    }

    public String getMessage() { return message; }

    public Date getDate() {
        return date;
    }


    public void setMessage(String message) throws TweetTooLongException {
        if (message.length() > 140) {
            throw new TweetTooLongException();
        }
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString(){
        return date.toString() + " | " + message;
    }
}
