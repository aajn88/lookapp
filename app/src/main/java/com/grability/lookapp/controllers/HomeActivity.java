package com.grability.lookapp.controllers;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.inject.Inject;
import com.grability.lookapp.R;
import com.grability.lookapp.controllers.categories.whole_world.WholeWorldFragment;
import com.grability.lookapp.controllers.common.BaseActivity;
import com.grability.lookapp.services.api.IAppsService;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * This is the Home Activity where the mainly view of the App is shown
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio A. Jimenez N.</a>
 */
@ContentView(R.layout.activity_home)
public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        WholeWorldFragment.OnCategoryRequestedListener {

    /** Apps Service **/
    @Inject
    private IAppsService appsService;

    /** Main Toolbar **/
    @InjectView(R.id.toolbar)
    private Toolbar mToolbar;

    /** Main Drawer Layout **/
    @InjectView(R.id.drawer_layout)
    private DrawerLayout mDrawer;

    /** Main Navigation View **/
    @InjectView(R.id.nav_view)
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
            case R.id.nav_travel:
                break;
            case R.id.nav_social:
                break;
            case R.id.nav_photo_video:
                break;
            case R.id.nav_navigation:
                break;
            case R.id.nav_music:
                break;
            case R.id.nav_games:
                break;
            case R.id.nav_entertainment:
                break;
            case R.id.nav_education:
                break;
            case R.id.nav_share:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content_rl, fragment).commit();
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * This method will be used when a category is requested
     *
     * @param categoryId
     *         Category Id that must to be shown
     */
    @Override
    public void onCategoryRequestedListener(int categoryId) {

    }
}
