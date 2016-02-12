package com.grability.lookapp.controllers.common;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.grability.lookapp.R;
import com.grability.lookapp.model.app.App;
import com.grability.lookapp.utils.AttrsManager;
import com.grability.lookapp.utils.ImageUtils;
import com.grability.lookapp.utils.LookappUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This adapter shows the apps depending on the device screen. A medium to small screen, they are
 * shown in a listview, in large screens they are shown in a gridview
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class AppsAdapter extends ArrayAdapter<App> {

    /** Tag for Logs **/
    private static final String TAG_LOG = AppsAdapter.class.getName();

    /** The original dataset **/
    private final List<App> mApps;
    /** The filter to be applied **/
    private final Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<App> apps = new ArrayList<App>();

            String[] words = constraint.toString().split(" +");
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].toUpperCase();
            }

            for (App app : mApps) {
                String appName = AttrsManager.getLabel(app.getName()).toUpperCase();
                boolean match = true;
                for (int i = 0; i < words.length && match; i++) {
                    match = appName.contains(words[i]);
                }
                if (match) {
                    apps.add(app);
                }
            }

            results.count = apps.size();
            results.values = apps;

            Log.d(TAG_LOG, String.format("Filtered apps: %s", apps));

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List<App>) results.values);
            notifyDataSetChanged();
        }
    };
    /** Layout Inflater **/
    private LayoutInflater mInflater;

    /**
     * Constructor
     *
     * @param context
     *         The current context.
     * @param apps
     *         The objects to represent in the ListView.
     */
    public AppsAdapter(Context context, App[] apps) {
        super(context, R.layout.app_google_card, apps);
        mApps = new ArrayList<>(apps.length);
        Collections.addAll(mApps, apps);
        init();
    }

    /**
     * Constructor
     *
     * @param context
     *         The current context.
     * @param apps
     *         The objects to represent in the ListView.
     */
    public AppsAdapter(Context context, List<App> apps) {
        super(context, R.layout.app_google_card, apps);
        mApps = new ArrayList<>(apps);
        init();
    }

    /**
     * Inits the basic fields
     */
    private void init() {
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            int layout = LookappUtils.isTablet(getContext()) ? R.layout.app_google_card :
                    R.layout.app_list_item;
            convertView = mInflater.inflate(layout, parent, false);
            holder = new Holder();

            holder.image = (ImageView) convertView.findViewById(R.id.app_image_siv);
            holder.title = (TextView) convertView.findViewById(R.id.app_title_rtv);
            holder.caption = (TextView) convertView.findViewById(R.id.app_caption_rtv);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        App app = getItem(position);

        ImageUtils.displayImage(holder.image, AttrsManager.getLabel(app.getImages()[2]), null);
        holder.title.setText(AttrsManager.getLabel(app.getName()));
        holder.caption.setText(AttrsManager.getLabel(app.getSummary()));

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    /**
     * This is the Holder class for HolderView pattern
     */
    private class Holder {
        ImageView image;
        TextView title;
        TextView caption;
    }
}
