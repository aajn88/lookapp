package com.grability.lookapp.controllers.common;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.google.inject.Inject;
import com.grability.lookapp.R;
import com.grability.lookapp.model.app.App;
import com.grability.lookapp.model.feed.Feed;
import com.grability.lookapp.services.api.IAppsService;
import com.grability.lookapp.utils.LookappUtils;
import com.grability.lookapp.utils.ViewUtils;
import com.nhaarman.listviewanimations.appearance.AnimationAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * The apps viewer fragment for list view or grid view
 */
public class AppsViewerFragment extends RoboFragment implements IFilterSubscriber {

    /** Apps Container **/
    @InjectView(R.id.apps_container)
    private AbsListView mAppsContainer;

    /** Apps Service **/
    @Inject
    private IAppsService appsService;

    /** Apps Adapter **/
    private AppsAdapter mAdapter;

    /** Filter Register **/
    private IFilterRegister mFilterRegister;

    /** Default constructor **/
    public AppsViewerFragment() {
        // Required empty public constructor
    }

    /**
     * Requests a new instance
     *
     * @return New instance
     */
    public static AppsViewerFragment newInstance() {
        return new AppsViewerFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFilterRegister) {
            mFilterRegister = ((IFilterRegister) context);
            mFilterRegister.register(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mFilterRegister != null) {
            mFilterRegister.register(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mFilterRegister != null) {
            mFilterRegister.unregister(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layout = LookappUtils.isTablet(getActivity()) ? R.layout.app_grid_layout :
                R.layout.app_list_view_layout;
        return inflater.inflate(layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new LoadAppsAsyncTask().execute();
    }

    /**
     * This method will be called when the filter is changed
     *
     * @param s
     *         The new filter request
     */
    @Override
    public void filter(CharSequence s) {
        mAdapter.getFilter().filter(s);
    }

    /**
     * This class loads the apps asynchronously
     */
    private class LoadAppsAsyncTask extends AsyncTask<Void, Void, List<App>> {

        @Override
        protected List<App> doInBackground(Void... params) {
            Feed feed = appsService.getFeed();
            return feed == null ? null : feed.getApps();
        }

        @Override
        protected void onPostExecute(List<App> apps) {
            super.onPostExecute(apps);

            if (apps == null) {
                // TODO: Show error
                return;
            }

            List<App> copiedList = new ArrayList<>(apps);
            mAdapter = new AppsAdapter(getActivity(), copiedList);
            AnimationAdapter animAdapter = ViewUtils
                    .animateAdapter(new Random().nextInt(5), mAdapter);
            animAdapter.setAbsListView(mAppsContainer);
            mAppsContainer.setAdapter(animAdapter);

        }
    }

}
