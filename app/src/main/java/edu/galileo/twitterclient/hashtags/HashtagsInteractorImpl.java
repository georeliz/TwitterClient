package edu.galileo.twitterclient.hashtags;

/**
 * Created by Lab1 on 20/06/2016.
 */
public class HashtagsInteractorImpl implements HashtagsInteractor {

    HashtagsRepository repository;
    public HashtagsInteractorImpl(HashtagsRepository repository) {
        this.repository = repository;

    }

    @Override
    public void execute() {
        repository.getHashtags();

    }
}
