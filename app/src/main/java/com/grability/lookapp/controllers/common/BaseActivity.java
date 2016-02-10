package com.grability.lookapp.controllers.common;

import android.os.Bundle;

import com.grability.lookapp.utils.LookappUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import roboguice.activity.RoboActionBarActivity;

/**
 * This is the base activity where all basic funcionalities are loaded. Such as, Image loader,
 * screen orientation based on screen size, etc.
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class BaseActivity extends RoboActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageLoader imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited()) {
            imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        }

        LookappUtils.adjustScreenOrientation(this);
    }
}
