package edu.galileo.twitterclient.lib.di;



import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.twitterclient.lib.GlideImageLoader;
import edu.galileo.twitterclient.lib.GreenRobotEventBus;
import edu.galileo.twitterclient.lib.base.EventBus;
import edu.galileo.twitterclient.lib.base.ImageLoader;

/**
 * Created by Gerson on 17/06/2016.
 */
@Module
public class LibsModule {
    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment){
        return Glide.with(fragment);
    }
    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }


    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
