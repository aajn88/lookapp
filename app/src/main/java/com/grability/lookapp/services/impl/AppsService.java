package com.grability.lookapp.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.grability.lookapp.clients.ILookAppClient;
import com.grability.lookapp.constants.AppAttribute;
import com.grability.lookapp.managers.api.IAppsManager;
import com.grability.lookapp.managers.api.IFeedsManager;
import com.grability.lookapp.model.app.App;
import com.grability.lookapp.model.app.Category;
import com.grability.lookapp.model.feed.Feed;
import com.grability.lookapp.model.rest.FeedResponse;
import com.grability.lookapp.services.api.IAppsService;
import com.grability.lookapp.utils.AttributesManager;
import com.grability.lookapp.utils.DateUtils;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class implements {@link IAppsService} interface that offers Apps services
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class AppsService implements IAppsService {

    /** Feed Manager **/
    @Inject
    private IFeedsManager feedManager;

    /** App Manager **/
    @Inject
    private IAppsManager appManager;

    /** LookApp server Client **/
    @Inject
    private ILookAppClient lookAppClient;

    /** This is the current Feed **/
    private Feed currentFeed;

    /** Categories List **/
    private List<Category> categories;

    /**
     * This method requests feed. The resulting feed is stored in the DB and then is returned while
     * the online feed is not modified
     *
     * @return Resulting feed. If an error occurs, then null is returned
     */
    @Override
    public Feed getFeed() {

        if (currentFeed == null) {
            List<Feed> feeds = feedManager.all();
            if (!feeds.isEmpty()) {
                currentFeed = feeds.get(0);
                currentFeed.setApps(appManager.all());
            }
        }
        FeedResponse response = lookAppClient.requestFeed();
        if (response == null && currentFeed == null) {
            return null;
        }

        if (response != null && (currentFeed == null ||
                currentFeed != null && compareUpdateFeed(response.getFeed(), currentFeed) > 0)) {
            currentFeed = response.getFeed();
            cleanFields();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    updateFeed(currentFeed);
                }
            }).start();
        }

        return currentFeed;
    }

    /**
     * This method cleans all related fields to the Current Feed
     */
    private void cleanFields() {
        categories = null;
    }

    /**
     * This method returns all loaded categories from the Feed
     *
     * @return Returns a List of Categories. If an error occurs, then null is returned
     */
    @Override
    public List<Category> getCategories() {
        return getCategories(false);
    }

    /**
     * This method returns all loaded categories from the Feed. It is possible to force offline
     * loaded, therefore internet connection will not be required, but at least a feed must to be
     * stored in the DB. This parameter would be useful when categories are needed in Main Thread
     *
     * @param forceOffline
     *         True to load stored categories in DB. Otherwise feed would be loaded using internet
     *         connection.
     *
     * @return Returns a List of Categories. If an error occurs, then null is returned
     */
    @Override
    public List<Category> getCategories(boolean forceOffline) {
        if (categories != null) {
            return categories;
        }
        Feed feed = forceOffline ? currentFeed : getFeed();
        if (feed == null) {
            return null;
        }
        categories = new ArrayList<Category>();
        Set<Integer> categoriesSet = new HashSet<Integer>();
        for (App app : feed.getApps()) {
            int categoryId = AttributesManager.getInt(app.getCategory(), AppAttribute.ID);
            if (!categoriesSet.contains(categoryId)) {
                categoriesSet.add(categoryId);

                String label = AttributesManager.getString(app.getCategory(), AppAttribute.LABEL);
                categories.add(new Category(categoryId, label));
            }
        }

        return categories;
    }

    /**
     * This method requests a List of apps of a given category
     *
     * @param categoryId
     *         Requested Category
     *
     * @return List of apps from the requested category. If an error occurs, then null will be
     * returned
     */
    @Override
    public List<App> getAppsByCategory(int categoryId) {
        return getAppsByCategory(categoryId, -1);
    }

    /**
     * This method requests a List of apps of a given category and a limit of it.
     *
     * @param categoryId
     *         Requested Category
     * @param limit
     *         Limit of apps. -1 will return all apps from the category
     *
     * @return List of apps from the requested category. The amount of returned apps are given from
     * the limit, if the limit is grater than the total amount of apps, then all the apps are
     * returned. If an error occurs, then null will be returned
     */
    @Override
    public List<App> getAppsByCategory(int categoryId, int limit) {

        Validate.isTrue(limit > 0, "The apps limit should by grater than 0");

        if (currentFeed == null) {
            return null;
        }

        List<App> filteredApps = new ArrayList<>();
        for (App app : currentFeed.getApps()) {
            if (filteredApps.size() == limit) {
                return filteredApps;
            }

            int appCatId = AttributesManager.getInt(app.getCategory(), AppAttribute.ID);
            if (categoryId == appCatId) {
                filteredApps.add(app);
            }
        }
        return filteredApps;
    }

    /**
     * This method updates the DB with new feed
     *
     * @param newFeed
     *         The feed to be stored
     */
    private void updateFeed(Feed newFeed) {
        feedManager.deleteAll();
        appManager.deleteAll();

        feedManager.createOrUpdate(newFeed);
        for (App app : newFeed.getApps()) {
            appManager.createOrUpdate(app);
        }
    }

    /**
     * This method compares two update dates of two given feeds
     *
     * @param f1
     *         First feed
     * @param f2
     *         Second feed
     *
     * @return < 0 if {@code f1} is lesser than {@code f1}. 0 if both are the same. > 0 if {@code
     * f1} is grater than {@code f1}
     */
    private int compareUpdateFeed(Feed f1, Feed f2) {
        Date dateF1 = DateUtils.parseDate(f1.getUpdated().getLabel(), DateUtils.DEFAULT_FORMAT);
        Date dateF2 = DateUtils.parseDate(f2.getUpdated().getLabel(), DateUtils.DEFAULT_FORMAT);

        return dateF1.compareTo(dateF2);
    }

}
