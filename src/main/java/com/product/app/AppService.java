package com.product.app;

import com.product.app.dao.PersonDAO;
import com.product.app.health.TemplateCheck;
import com.product.app.resources.HelloWorldResource;
import com.product.app.resources.PersonResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI;

public class AppService extends Service<AppConfiguration> {

    public static void main(String[] args) throws Exception{
        new AppService().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
    }

    @Override
    public void run(AppConfiguration config, Environment environment) throws Exception {
        final HelloWorldResource helloWorldResource = new HelloWorldResource(config.getTemplate(), config.getDefaultName());
        final TemplateCheck templateCheck = new TemplateCheck("TemplateCheck",config.getTemplate());
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDatabase(),"Postgres");
        final PersonDAO personDAO = jdbi.onDemand(PersonDAO.class);
        final PersonResource personResource = new PersonResource(personDAO);

        environment.addResource(helloWorldResource);
        environment.addResource(personResource);
        environment.addHealthCheck(templateCheck);
    }
}
