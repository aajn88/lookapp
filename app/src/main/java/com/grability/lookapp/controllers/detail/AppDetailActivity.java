package com.grability.lookapp.controllers.detail;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.grability.lookapp.R;
import com.grability.lookapp.constants.AppAttribute;
import com.grability.lookapp.controllers.common.BaseActivity;
import com.grability.lookapp.model.app.App;
import com.grability.lookapp.utils.AttrsManager;
import com.grability.lookapp.utils.ImageUtils;
import com.grability.lookapp.views.circular_progress_bar.CircularProgressButton;

import java.util.Random;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_app_detail_content)
public class AppDetailActivity extends BaseActivity {

    /** Key for the selected App **/
    public static final String SELECTED_APP_KEY = "SELECTED_APP_KEY";

    /** Currency Format **/
    private static final String CURRENCY_FORMAT = "%1$,.2f";

    /** Tag for Logs **/
    private static final String TAG_LOG = AppDetailActivity.class.getName();

    /** Random instance **/
    private final Random mRandom = new Random();

    /** App Image **/
    @InjectView(R.id.app_image_siv)
    private ImageView mAppImageSiv;

    /** Download Btn **/
    @InjectView(R.id.download_btn)
    private CircularProgressButton mDownloadBtn;

    /** Summary Content TextView **/
    @InjectView(R.id.summary_content_tv)
    private TextView mSummaryContentTv;

    /** App Title **/
    @InjectView(R.id.app_title_rtv)
    private TextView mAppTitleRtv;

    /** App Rights TextView **/
    @InjectView(R.id.app_rights_rtv)
    private TextView mRightsRtv;

    /** Down Left Arrow **/
    @InjectView(R.id.summary_left_arrow_tv)
    private View mDownLeftArrow;

    /** Down Left Arrow **/
    @InjectView(R.id.summary_right_arrow_tv)
    private View mDownRightArrow;

    /** Summary RelativeLayout **/
    @InjectView(R.id.summary_rl)
    private View mSummaryRl;

    /** Toolbar **/
    @InjectView(R.id.toolbar)
    private Toolbar mToolbar;

    /** Selected APP **/
    private App mSelectedApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });

        mSelectedApp = (App) getIntent().getSerializableExtra(SELECTED_APP_KEY);

        mDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDownloadBtn.getProgress() != 100) {
                    new SimulateProgress(mDownloadBtn).execute(mRandom.nextBoolean() ? 100 : -1);
                }
            }
        });

        mSummaryRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAnimation();
            }
        });

        loadApp();
    }

    /**
     * This activity finishes properly the activity
     */
    protected void finishActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    /**
     * This method loads the animations
     */
    private void loadAnimation() {
        int animId = R.anim.slide_down;
        int visibility = View.VISIBLE;
        int flechaId = R.anim.rotate_180_degrees;

        if (mSummaryContentTv.isShown()) {
            animId = R.anim.slide_up;
            visibility = View.GONE;
            flechaId = R.anim.rotate_360_degrees_from_180;
        }

        Animation anim = AnimationUtils.loadAnimation(this, animId);
        final int finalVisibility = visibility;
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                mSummaryContentTv.setVisibility(finalVisibility);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        mSummaryContentTv.startAnimation(anim);
        if (visibility == View.VISIBLE) {
            mSummaryContentTv.setVisibility(visibility);
        }

        Animation animRotate = AnimationUtils.loadAnimation(this, flechaId);
        mDownLeftArrow.startAnimation(animRotate);
        mDownRightArrow.startAnimation(animRotate);
    }

    /**
     * This method loads app information
     */
    private void loadApp() {
        mAppTitleRtv.setText(AttrsManager.getLabel(mSelectedApp.getName()));
        mSummaryContentTv.setText(AttrsManager.getLabel(mSelectedApp.getSummary()));
        String url = AttrsManager.getLabel(mSelectedApp.getImages()[2]);
        ImageUtils.displayImage(mAppImageSiv, url, null);

        float price = AttrsManager.getFloat(mSelectedApp.getPrice(), AppAttribute.AMOUNT);
        String priceStr = (price == 0.0f ? getString(R.string.free_caps) :
                String.format(CURRENCY_FORMAT, price));
        mDownloadBtn.setIdleText(priceStr);
        mRightsRtv.setText(AttrsManager.getLabel(mSelectedApp.getRights()));
    }

    /**
     * This class simulates the progress of the download
     */
    private class SimulateProgress extends AsyncTask<Integer, Integer, Integer> {

        final CircularProgressButton cpb;

        public SimulateProgress(CircularProgressButton cpb) {
            this.cpb = cpb;
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            for (int i = 0; i < 100; i += 5) {
                publishProgress(i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Log.e(TAG_LOG, "An error has occurred while sleeping", e);
                }
            }
            return params[0];
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            cpb.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            cpb.setProgress(result);
        }
    }
}
