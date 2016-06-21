package edu.galileo.twitterclient;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;


import edu.galileo.twitterclient.hashtags.di.DaggerHashtagsComponent;
import edu.galileo.twitterclient.hashtags.di.HashtagsModule;
import edu.galileo.twitterclient.hashtags.di.HashtagsComponent;
import edu.galileo.twitterclient.hashtags.ui.HashtagsView;
import edu.galileo.twitterclient.images.di.DaggerImagesCompenent;
import edu.galileo.twitterclient.images.di.ImagesCompenent;
import edu.galileo.twitterclient.images.di.ImagesModule;
import edu.galileo.twitterclient.images.ui.ImagesView;
import edu.galileo.twitterclient.lib.di.LibsModule;
import io.fabric.sdk.android.Fabric;

/**
 * Created by Gerson on 17/06/2016.
 */
public class TwiiterClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWIITER_KEY,BuildConfig.TWIITER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    public ImagesCompenent getImagesComponent(Fragment fragment, ImagesView view, edu.galileo.twitterclient.images.adapters.OnItemClickListener clickListener){
        return DaggerImagesCompenent.builder().libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, clickListener)).build();
    }


    public HashtagsComponent getHashtagsComponent(HashtagsView view, edu.galileo.twitterclient.hashtags.adapters.OnItemClickListener clickListener){
        return DaggerHashtagsComponent.builder().libsModule(new LibsModule(null))
                .hashtagsModule(new HashtagsModule(view, clickListener)).build();
    }


}
