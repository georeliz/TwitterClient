package edu.galileo.twitterclient.hashtags.di;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.twitterclient.entities.Hashtag;
import edu.galileo.twitterclient.hashtags.HashtagsInteractor;
import edu.galileo.twitterclient.hashtags.HashtagsInteractorImpl;
import edu.galileo.twitterclient.hashtags.HashtagsPresenter;
import edu.galileo.twitterclient.hashtags.HashtagsPresenterImpl;
import edu.galileo.twitterclient.hashtags.HashtagsRepository;
import edu.galileo.twitterclient.hashtags.HashtagsRepositoryImpl;
import edu.galileo.twitterclient.hashtags.adapters.HashtagsAdapter;
import edu.galileo.twitterclient.hashtags.adapters.OnItemClickListener;
import edu.galileo.twitterclient.hashtags.ui.HashtagsView;
import edu.galileo.twitterclient.lib.base.EventBus;

/**
 * Created by Lab1 on 20/06/2016.
 */
@Module
public class HashtagsModule {
    private HashtagsView view;
    private OnItemClickListener clickListener;

    public HashtagsModule(HashtagsView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    HashtagsAdapter providesAdapter(List<Hashtag> dataset, OnItemClickListener clickListener){
        return new HashtagsAdapter(dataset, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Hashtag> providesItemList(){
        return new ArrayList<Hashtag>();
    }

    @Provides
    @Singleton
    HashtagsPresenter providesImagesPresenter(HashtagsView view, EventBus eventBus, HashtagsInteractor interactor){
        return new HashtagsPresenterImpl(view,eventBus, interactor);
    }

    @Provides
    @Singleton
    HashtagsView providesHashtagsView(){
        return this.view;
    }

    @Provides
    @Singleton
    HashtagsInteractor providesHastagsInteractor(HashtagsRepository repository){
        return new HashtagsInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashtagsRepository providesHashtagsRepository(EventBus eventBus, CustomTwitterApiClient client){
        return new HashtagsRepositoryImpl(eventBus, client);
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
