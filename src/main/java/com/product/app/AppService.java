package com.product.app;

import com.product.app.resources.HelloWorldResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class AppService extends Service<AppConfiguration> {

    public static void main(String[] args) throws Exception{
        new AppService().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
    }

    @Override
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {
        environment.addResource(new HelloWorldResource());
    }
}
