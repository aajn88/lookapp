package com.grability.lookapp.controllers.categories.whole_world;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.inject.Inject;
import com.grability.lookapp.R;
import com.grability.lookapp.model.app.Category;
import com.grability.lookapp.services.api.IAppsService;
import com.grability.lookapp.utils.ViewUtils;
import com.nhaarman.listviewanimations.appearance.AnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;

import java.util.List;
import java.util.Random;

import roboguice.fragment.provided.RoboFragment;
import roboguice.inject.InjectView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link OnCategoryRequestedListener} interface to handle interaction events. Use the {@link
 * WholeWorldFragment#newInstance} factory method to create an instance of this fragment.
 */
public class WholeWorldFragment extends RoboFragment {

    /** Listener for Category calls **/
    private OnCategoryRequestedListener mListener;

    /** Categories DynamicListView **/
    @InjectView(R.id.categories_dlv)
    private DynamicListView mCategoriesDlv;

    /** Apps Service **/
    @Inject
    private IAppsService appsService;

    /** Default constructor **/
    public WholeWorldFragment() {
        // Required empty public constructor
    }

    /**
     * This method creates a new Whole World Fragment instance
     */
    public static WholeWorldFragment newInstance() {
        return new WholeWorldFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_whole_world, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new InitialLoadingAsyncTask().execute();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCategoryRequestedListener) {
            mListener = (OnCategoryRequestedListener) context;
        } else {
            throw new RuntimeException(
                    context.toString() + " must implement OnCategoryRequestedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * All activities that want to receive categories requests must to implement this interface
     */
    public interface OnCategoryRequestedListener {

        /**
         * This method will be used when a category is requested
         *
         * @param categoryId
         *         Category Id that must to be shown
         */
        void onCategoryRequestedListener(int categoryId);
    }

    /**
     * This class does the initial loading, that is load all categories and its views
     */
    private class InitialLoadingAsyncTask extends AsyncTask<Void, Void, List<Category>> {

        @Override
        protected List<Category> doInBackground(Void... params) {
            return appsService.getCategories();
        }

        @Override
        protected void onPostExecute(List<Category> categories) {
            super.onPostExecute(categories);
            if (categories == null) {
                // TODO: Show error
            }
            CategoriesAdapter adapter = new CategoriesAdapter(getActivity(), categories);
            AnimationAdapter animAdapter = ViewUtils
                    .animateAdapter(new Random().nextInt(5), adapter);
            animAdapter.setAbsListView(mCategoriesDlv);
            mCategoriesDlv.setAdapter(animAdapter);
        }
    }
}
