package edu.galileo.twitterclient.hashtags.di;

import javax.inject.Singleton;

import dagger.Component;

import edu.galileo.twitterclient.hashtags.ui.HashtagsFragment;
import edu.galileo.twitterclient.lib.di.LibsModule;

/**
 * Created by Lab1 on 20/06/2016.
 */
@Singleton @Component(modules = {LibsModule.class, HashtagsModule.class})
public interface HashtagsComponent {
    void inject(HashtagsFragment fragment);
}
