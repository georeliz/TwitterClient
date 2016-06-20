package edu.galileo.twitterclient.hashtags;

import edu.galileo.twitterclient.hashtags.events.HashtagsEvent;

/**
 * Created by Lab1 on 20/06/2016.
 */
public interface HashtagsPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onMeanEventThread(HashtagsEvent event);
}
