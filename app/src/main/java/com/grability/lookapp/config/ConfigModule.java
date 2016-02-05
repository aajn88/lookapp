package com.grability.lookapp.config;

import com.google.inject.AbstractModule;
import com.grability.lookapp.clients.ILookAppClient;
import com.grability.lookapp.clients.LookAppClient;
import com.grability.lookapp.services.api.IAppsService;
import com.grability.lookapp.services.impl.AppsService;

/**
 * This is the configuration module for RoboGuice dependencis. Here is where Interfaces and their
 * implementation are bind.
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class ConfigModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IAppsService.class).to(AppsService.class);
        bind(ILookAppClient.class).to(LookAppClient.class);
    }
}
