package edu.galileo.twitterclient.images;

/**
 * Created by Gerson on 19/06/2016.
 */
public class ImagesInteractorImpl implements ImagesInteractor{
   ImagesRepository repository;

    public ImagesInteractorImpl(ImagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getImages();

    }
}
