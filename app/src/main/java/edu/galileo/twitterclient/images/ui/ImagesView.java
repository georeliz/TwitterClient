package edu.galileo.twitterclient.images.ui;

import java.util.List;

import edu.galileo.twitterclient.entities.Image;

/**
 * Created by Gerson on 19/06/2016.
 */
public interface ImagesView {
    void showElements();
    void hideElements();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Image> items);
}
