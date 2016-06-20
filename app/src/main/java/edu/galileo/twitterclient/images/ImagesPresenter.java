package edu.galileo.twitterclient.images;

import edu.galileo.twitterclient.images.events.ImagesEvent;

/**
 * Created by Gerson on 19/06/2016.
 */
public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImageTweets();
    void onMeanEventThread(ImagesEvent event);
}
