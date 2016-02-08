package com.grability.lookapp.config;

import com.google.inject.AbstractModule;
import com.grability.lookapp.clients.ILookAppClient;
import com.grability.lookapp.clients.LookAppClient;
import com.grability.lookapp.managers.api.IAppsManager;
import com.grability.lookapp.managers.api.IFeedsManager;
import com.grability.lookapp.managers.impl.AppsManager;
import com.grability.lookapp.managers.impl.FeedsManager;
import com.grability.lookapp.services.api.IAppsService;
import com.grability.lookapp.services.impl.AppsService;

/**
 * This is the configuration module for RoboGuice dependencies. Here is where Interfaces and their
 * implementation are bind.
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class ConfigModule extends AbstractModule {

    @Override
    protected void configure() {
        bindServices();
        bindManagers();
        bindOthers();
    }

    /**
     * This method binds all services
     */
    private void bindServices() {
        bind(IAppsService.class).to(AppsService.class);
    }

    /**
     * This method binds all managers
     */
    private void bindManagers() {
        bind(IFeedsManager.class).to(FeedsManager.class);
        bind(IAppsManager.class).to(AppsManager.class);
    }

    /**
     * This method binds other classes, such as clients and internal classes
     */
    private void bindOthers() {
        bind(ILookAppClient.class).to(LookAppClient.class);
    }
}
