package com.grability.lookapp.controllers;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.grability.lookapp.R;
import com.grability.lookapp.controllers.categories.whole_world.WholeWorldFragment;
import com.grability.lookapp.controllers.common.AppsViewerFragment;
import com.grability.lookapp.controllers.common.BaseActivity;
import com.grability.lookapp.controllers.common.IFilterRegister;
import com.grability.lookapp.controllers.common.IFilterSubscriber;
import com.grability.lookapp.services.api.IAppsService;
import com.grability.lookapp.utils.AnimationsUtils;
import com.grability.lookapp.utils.ImageUtils;
import com.grability.lookapp.utils.LookappUtils;

import java.util.HashSet;
import java.util.Set;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * This is the Home Activity where the mainly view of the App is shown
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio A. Jimenez N.</a>
 */
@ContentView(R.layout.activity_home)
public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, IFilterRegister {

    /** Duration of animation search in millis **/
    private static final int DURATION_ANIM_SEARCH = 400;

    /** Apps Service **/
    @Inject
    private IAppsService appsService;

    /** Main Toolbar **/
    @InjectView(R.id.toolbar)
    private Toolbar mToolbar;

    /** Lookapp Logo **/
    @InjectView(R.id.lookapp_logo_iv)
    private ImageView mLookappLogoIv;

    /** Searcher LinearLayout **/
    @InjectView(R.id.searcher_ll)
    private View mSearcherLl;

    /** Searcher back Tv **/
    @InjectView(R.id.searcher_back_mditv)
    private TextView mSearcherBackTv;

    /** Clear Search Box Tv **/
    @InjectView(R.id.clear_mditv)
    private TextView mClearSearchTv;

    /** Searcher EditText **/
    @InjectView(R.id.searcher_et)
    private EditText mSearcherEt;

    /** Main Drawer Layout **/
    @InjectView(R.id.drawer_layout)
    private DrawerLayout mDrawer;

    /** Main Navigation View **/
    @InjectView(R.id.nav_view)
    private NavigationView mNavigationView;

    /** The Filter subscribers **/
    private Set<IFilterSubscriber> subscribers = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);

        ViewCompat.setElevation(mToolbar, getResources().getDimension(R.dimen.toolbar_elevation));
        ViewCompat
                .setElevation(mSearcherLl, getResources().getDimension(R.dimen.toolbar_elevation));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
        ImageView userPic = (ImageView) mNavigationView.getHeaderView(0)
                .findViewById(R.id.user_pic_siv);
        ImageUtils.displayImage(userPic, getString(R.string.author_image), null);

        mSearcherLl.setVisibility(View.GONE);

        mLookappLogoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableSearcher();
            }
        });

        setUpSearch();

        MenuItem item = mNavigationView.getMenu().getItem(0);
        item.setChecked(true);
        onNavigationItemSelected(item);
    }

    /**
     * This method sets up the search action
     */
    private void setUpSearch() {
        mSearcherEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mClearSearchTv.setVisibility(count == 0 ? View.GONE : View.VISIBLE);
                for (IFilterSubscriber subscriber : subscribers) {
                    subscriber.filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mClearSearchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearcherEt.setText("");
            }
        });

        mSearcherBackTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableSearcher();
            }
        });
    }

    /**
     * This method disables the Searcher Bar
     */
    private void disableSearcher() {
        int[] location = new int[2];
        mLookappLogoIv.getLocationOnScreen(location);
        final FragmentManager fm = getSupportFragmentManager();
        if (LookappUtils.isGraterLollipop()) {
            Animator anim = AnimationsUtils
                    .animateCircleRevealShow(mSearcherLl, location[0], location[1],
                            DURATION_ANIM_SEARCH, true);

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {}

                @Override
                public void onAnimationEnd(Animator animation) {
                    mSearcherLl.setVisibility(View.GONE);
                    fm.popBackStack();
                }

                @Override
                public void onAnimationCancel(Animator animation) {}

                @Override
                public void onAnimationRepeat(Animator animation) {}
            });
            anim.start();
            mSearcherEt.setText("");
        } else {
            mSearcherLl.setVisibility(View.GONE);
            fm.popBackStack();
        }
    }

    /**
     * This method enables the Searcher bar
     */
    private void enableSearcher() {
        int[] location = new int[2];
        mLookappLogoIv.getLocationOnScreen(location);
        if (LookappUtils.isGraterLollipop()) {
            AnimationsUtils.animateCircleRevealShow(mSearcherLl, location[0], location[1],
                    DURATION_ANIM_SEARCH).start();
        } else {
            mSearcherLl.setVisibility(View.VISIBLE);
        }

        AppsViewerFragment fragment = AppsViewerFragment.newInstance();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_content_rl, fragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (mSearcherLl.isShown()) {
            disableSearcher();
        } else if (fm.getBackStackEntryCount() != 0) {
            fm.popBackStack();
        } else {
            finishAffinity();
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        switch (id) {
            case R.id.nav_whole_world:
                fragment = WholeWorldFragment.newInstance();
                break;
            case R.id.nav_share:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content_rl, fragment).commit();
            if (fragment instanceof IFragmentInfo) {
                getSupportActionBar().setTitle(((IFragmentInfo) fragment).getMenuNameId());
            }
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * This method registers a "subscriber" from filter actions
     *
     * @param subscriber
     *         The subscriber
     */
    @Override
    public void register(IFilterSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * This interface is used to request basic fragment information
     */
    public interface IFragmentInfo {

        /**
         * This method returns the Fragment Name Resource Id
         *
         * @return The String Res Id of the name of the fragment
         */
        @StringRes
        int getMenuNameId();
    }

    /**
     * This method unregister a subscriber from filter actions
     *
     * @param subscriber
     *         The subscriber to be unregistered
     */
    @Override
    public void unregister(IFilterSubscriber subscriber) {
        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        }
    }
}
