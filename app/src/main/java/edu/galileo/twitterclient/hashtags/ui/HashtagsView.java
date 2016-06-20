package edu.galileo.twitterclient.hashtags.ui;

import java.util.List;

import edu.galileo.twitterclient.entities.Hashtag;

/**
 * Created by Lab1 on 20/06/2016.
 */
public interface HashtagsView {
    void showElements();
    void hideElements();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Hashtag> items);
}
