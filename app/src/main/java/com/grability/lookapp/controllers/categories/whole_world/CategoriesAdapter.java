package com.grability.lookapp.controllers.categories.whole_world;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.inject.Inject;
import com.grability.lookapp.R;
import com.grability.lookapp.model.app.App;
import com.grability.lookapp.model.app.Category;
import com.grability.lookapp.services.api.IAppsService;

import java.util.List;

import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

/**
 * This adapter shows all categories passed as a parameter. For each category some apps will be
 * shown
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class CategoriesAdapter extends ArrayAdapter<Category> {

    /** The Layout Inflater **/
    private LayoutInflater inflater;

    /** Apps Service **/
    @Inject
    private IAppsService appsService;

    /**
     * Constructor
     *
     * @param context
     *         The current context.
     * @param objects
     *         The objects to represent in the ListView.
     */
    public CategoriesAdapter(Context context, List<Category> objects) {
        super(context, R.layout.category_snap_item_list, objects);
        injectFields(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * This method injects the custom fields
     *
     * @param context
     *         App context
     */
    private void injectFields(Context context) {
        final RoboInjector injector = RoboGuice.getInjector(context);
        // This will inject all fields marked with the @Inject annotation
        injector.injectMembersWithoutViews(this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        Category currentCategory = getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.category_snap_item_list, parent, false);
            holder = new Holder();
            holder.apps = appsService.getAppsByCategory(currentCategory.getId());

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        return convertView;
    }

    /**
     * View Holder pattern
     */
    private class Holder {

        List<App> apps;

    }
}
