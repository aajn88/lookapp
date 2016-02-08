package com.grability.lookapp.controllers;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.inject.Inject;
import com.grability.lookapp.R;
import com.grability.lookapp.services.api.IAppsService;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * This is the Home Activity where the mainly view of the App is shown
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio A. Jimenez N.</a>
 */
@ContentView(R.layout.activity_home)
public class HomeActivity extends RoboActionBarActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        switch (id) {
            case R.id.nav_whole_world:
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

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
