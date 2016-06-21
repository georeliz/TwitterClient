package edu.galileo.twitterclient.hashtags;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.twitterclient.entities.Hashtag;
import edu.galileo.twitterclient.hashtags.events.HashtagsEvent;
import edu.galileo.twitterclient.hashtags.ui.HashtagsView;
import edu.galileo.twitterclient.images.ui.ImagesView;
import edu.galileo.twitterclient.lib.base.EventBus;

/**
 * Created by Lab1 on 20/06/2016.
 */
public class HashtagsPresenterImpl implements HashtagsPresenter {

    private HashtagsView view;
    private EventBus eventBus;
    private HashtagsInteractor interactor;
    public HashtagsPresenterImpl(HashtagsView view, EventBus eventBus, HashtagsInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);

    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getHashtagTweets() {
        if(view != null){
            view.hideElements();
            view.showProgress();
        }
        interactor.execute();

    }

    @Override
    @Subscribe
    public void onMeanEventThread(HashtagsEvent event) {
        String errorMsg = event.getError();
        if(view != null){
            view.showElements();
            view.hideProgress();

            if (errorMsg != null){
                view.onError(errorMsg);
            }else {
                view.setContent(event.getHashtags());
            }
        }

    }
}
