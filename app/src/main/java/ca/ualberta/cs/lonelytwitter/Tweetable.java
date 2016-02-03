package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by romansky on 1/12/16.
 */

/**
 * Tweetable is an interface.
 * 
 * @see NormalTweet
 * @author Andrew Paquette, but like not rly
 * @version 1.57, 12/19/03
 * @since 2016-02-02
 */

public interface Tweetable {
    // getMessage returns the tweet message.
    public String getMessage();
    public Date getDate();
}
