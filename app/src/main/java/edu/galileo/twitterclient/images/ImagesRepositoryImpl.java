package edu.galileo.twitterclient.images;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.galileo.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.twitterclient.entities.Image;
import edu.galileo.twitterclient.images.events.ImagesEvent;
import edu.galileo.twitterclient.lib.base.EventBus;


/**
 * Created by Gerson on 19/06/2016.
 */
public class ImagesRepositoryImpl implements ImagesRepository {
    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;

    public ImagesRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getImages() {
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {

            @Override
            public void success(Result<List<Tweet>> result) {
                List<Image> items = new ArrayList<Image>();
                for (Tweet tweet : result.data){
                    if (containsImages(tweet)){
                        Image tweetModel = new Image();

                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);

                        String tweetText = tweet.text;
                        int index = tweetText.indexOf("http");
                        if (index > 0){
                            tweetText = tweetText.substring(0, index);
                        }
                        tweetModel.setTweetText(tweetText);

                        MediaEntity currentPhoto = tweet.entities.media.get(0);
                        String imageUrl = currentPhoto.mediaUrl;
                        tweetModel.setImageURL(imageUrl);

                        items.add(tweetModel);

                    }
                }
                Collections.sort(items, new Comparator<Image>() {
                    @Override
                    public int compare(Image image, Image t1) {
                        return t1.getFavoriteCount() - image.getFavoriteCount();
                    }
                });
                post(items);
            }

            @Override
            public void failure(TwitterException e) {
                post(e.getLocalizedMessage());
            }
        };

            client.getTimeLinseService().homeTimeline(TWEET_COUNT, true, true, true, true,callback);
    }

    private boolean containsImages(Tweet tweet){
        return tweet.entities != null && tweet.entities.media != null && !tweet.entities.media.isEmpty();

    }

    private void post(List<Image> items){
        post(items, null);

    }

    private void post( String error){
        post(null,error);

    }

    private void post(List<Image> items, String error){
        ImagesEvent event = new ImagesEvent();
        event.setError(error);
        event.setImages(items);
        eventBus.post(event);
    }
}
