package edu.galileo.twitterclient.lib;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import edu.galileo.twitterclient.lib.base.ImageLoader;

/**
 * Created by Gerson on 17/06/2016.
 */
public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequestManager;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    @Override
    public void load(ImageView imageView, String URL) {
        glideRequestManager.load(URL).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().override(600,400).into(imageView);
    }
}
