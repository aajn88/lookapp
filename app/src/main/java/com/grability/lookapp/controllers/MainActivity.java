package com.grability.lookapp.controllers;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.grability.lookapp.R;
import com.grability.lookapp.controllers.common.BaseActivity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

/**
 * This is the main activity where the splash screen is located
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio A. Jimenez N.</a>
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    /** Constant for animations duration **/
    private static final int ANIM_DURATION = 1000;

    /** Delay for the splash view **/
    private static final int SPLASH_DELAY = 2500;

    /** Lookapp Logo **/
    @InjectView(R.id.lookapp_logo_iv)
    private ImageView mLookappLogoIv;

    /** Transition Name **/
    @InjectResource(R.string.transition_view)
    private String mTransitionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fadeAnimation(mLookappLogoIv);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goHome();
            }
        }, SPLASH_DELAY);
    }

    /**
     * This method starts Home Activity. It starts specific transitions whether is Lollipop or not
     */
    private void goHome() {
        Intent loginIntent = new Intent(MainActivity.this, HomeActivity.class);

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, mLookappLogoIv, mTransitionView);
        startActivity(loginIntent, options.toBundle());
    }

    /**
     * This method creates a fade animation that suddenly appears "in front" of the screen to the
     * back of the screen
     */
    private void fadeAnimation(View view) {
        ObjectAnimator scaleXAnimation = createObjectAnimator(view, "scaleX", 5.0F, 1.0F);
        ObjectAnimator scaleYAnimation = createObjectAnimator(view, "scaleY", 5.0F, 1.0F);
        ObjectAnimator alphaAnimation = createObjectAnimator(view, "alpha", 0.0F, 1.0F);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.start();
    }

    /**
     * This method creates an Object Animator based on the targeted view, the property to be
     * animated and the initial value and final value
     *
     * @param view
     *         Target view
     * @param property
     *         Property to be animated
     * @param init
     *         Initial value
     * @param end
     *         Final value
     *
     * @return ObjectAnimator with the given animated property
     */
    @NonNull
    private ObjectAnimator createObjectAnimator(View view, String property, float init, float end) {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(view, property, init, end);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(ANIM_DURATION);
        return scaleXAnimation;
    }
}
