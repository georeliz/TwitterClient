package edu.galileo.twitterclient.images;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.twitterclient.images.events.ImagesEvent;
import edu.galileo.twitterclient.images.ui.ImagesView;
import edu.galileo.twitterclient.lib.base.EventBus;

/**
 * Created by Gerson on 19/06/2016.
 */
public class ImagesPresenterImpl implements ImagesPresenter{
    private ImagesView view;
    private EventBus eventBus;
    private ImagesInteractor interactor;

    public ImagesPresenterImpl(ImagesView view, EventBus eventBus, ImagesInteractor interactor) {
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
    public void getImageTweets() {
        if(view != null){
            view.hideElements();
            view.showProgress();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onMeanEventThread(ImagesEvent event) {
        String errorMsg = event.getError();
        if(view != null){
            view.showElements();
            view.hideElements();

            if (errorMsg != null){
                view.onError(errorMsg);
            }else {
                view.setContent(event.getImages());
            }
        }



    }
}
