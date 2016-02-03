package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by romansky on 1/12/16.
 */

/**
 * ImportantTweet is a variant of NormalTweet to
 * allow differentiation between tweet types.
 * @see NormalTweet
 * @author Andrew Paquette, but like not rly
 * @version 1.57, 12/19/03
 * @since 2016-02-02
 */

public class ImportantTweet extends Tweet implements Tweetable {
    @Override
    public Boolean isImportant() {
        return Boolean.TRUE;
    }

    public ImportantTweet(Date date, String message) {
        super(date, message);
    }

    public ImportantTweet(String message) {
        super(message);
    }

    public Date getDate() {
        return this.date;
    }

    public String getMessage() {
        return "!IMPORTANT! " + this.message;
    }
}
