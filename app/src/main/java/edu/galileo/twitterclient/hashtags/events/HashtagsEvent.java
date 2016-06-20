package edu.galileo.twitterclient.hashtags.events;

import java.util.List;

import edu.galileo.twitterclient.entities.Hashtag;

/**
 * Created by Lab1 on 20/06/2016.
 */
public class HashtagsEvent {
    private String error;
    private List<Hashtag> hashtags;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
