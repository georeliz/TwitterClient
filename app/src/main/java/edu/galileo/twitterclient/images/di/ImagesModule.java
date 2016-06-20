package edu.galileo.twitterclient.images.di;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.twitterclient.entities.Image;
import edu.galileo.twitterclient.images.ImagesInteractor;
import edu.galileo.twitterclient.images.ImagesInteractorImpl;
import edu.galileo.twitterclient.images.ImagesPresenter;
import edu.galileo.twitterclient.images.ImagesPresenterImpl;
import edu.galileo.twitterclient.images.ImagesRepository;
import edu.galileo.twitterclient.images.ImagesRepositoryImpl;
import edu.galileo.twitterclient.images.adapters.ImagesAdapter;
import edu.galileo.twitterclient.images.adapters.OnItemClickListener;
import edu.galileo.twitterclient.images.ui.ImagesView;
import edu.galileo.twitterclient.lib.base.EventBus;
import edu.galileo.twitterclient.lib.base.ImageLoader;

/**
 * Created by Gerson on 19/06/2016.
 */
@Module
public class ImagesModule {
    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener){
        return new ImagesAdapter(dataset, imageLoader, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Image> providesItemList(){
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(ImagesView view, EventBus eventBus, ImagesInteractor interactor){
        return new ImagesPresenterImpl(view,eventBus, interactor);
    }

    @Provides
    @Singleton
    ImagesView providesImagesView(){
        return this.view;
    }

    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository){
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImagesRepository providesImagesRepository(EventBus eventBus, CustomTwitterApiClient client){
        return new ImagesRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session session){
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesTwetterSession(){
        return Twitter.getSessionManager().getActiveSession();
    }
}
