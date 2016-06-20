package edu.galileo.twitterclient.images.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.twitterclient.images.ImagesPresenter;
import edu.galileo.twitterclient.images.ui.ImagesFragment;
import edu.galileo.twitterclient.lib.di.LibsModule;

/**
 * Created by Gerson on 19/06/2016.
 */
@Singleton @Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesCompenent {
    void inject(ImagesFragment fragment);
    ImagesPresenter getPresenter();
}
