package com.grability.lookapp.controllers.categories.whole_world;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grability.lookapp.R;

import roboguice.fragment.provided.RoboFragment;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link OnCategoryRequestedListener} interface to handle interaction events. Use the {@link
 * WholeWorldFragment#newInstance} factory method to create an instance of this fragment.
 */
public class WholeWorldFragment extends RoboFragment {

    /** Listener for Category calls **/
    private OnCategoryRequestedListener mListener;

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
}
