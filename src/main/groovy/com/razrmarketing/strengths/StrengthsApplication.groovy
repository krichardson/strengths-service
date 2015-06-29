package com.razrmarketing.strengths

import com.google.common.collect.ImmutableMap
import com.razrmarketing.strengths.dao.PersonDao
import com.razrmarketing.strengths.dao.PersonStrengthDao
import com.razrmarketing.strengths.dao.StrengthDao
import com.razrmarketing.strengths.module.PeopleModule
import com.razrmarketing.strengths.module.StrengthsModule
import com.razrmarketing.strengths.resources.PeopleResource
import com.razrmarketing.strengths.resources.StrengthsResource
import io.dropwizard.Application
import io.dropwizard.assets.AssetsBundle
import io.dropwizard.configuration.EnvironmentVariableSubstitutor
import io.dropwizard.configuration.SubstitutingSourceProvider
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.jdbi.DBIFactory
import io.dropwizard.migrations.MigrationsBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.federecio.dropwizard.swagger.SwaggerBundle
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration
import org.eclipse.jetty.servlets.CrossOriginFilter
import org.skife.jdbi.v2.DBI

import javax.servlet.DispatcherType

class StrengthsApplication extends Application<StrengthsConfiguration> {
    public static void main(String[] args) throws Exception {
        new StrengthsApplication().run(args)
    }

    @Override
    public void initialize(Bootstrap<StrengthsConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.configurationSourceProvider = new SubstitutingSourceProvider(
                bootstrap.configurationSourceProvider,
                new EnvironmentVariableSubstitutor(false)
        )
        bootstrap.addBundle(new MigrationsBundle<StrengthsConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(StrengthsConfiguration configuration) {
                return configuration.dataSourceFactory
            }
        })
        bootstrap.addBundle(new SwaggerBundle<StrengthsConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(StrengthsConfiguration configuration) {
                return configuration.swaggerBundleConfiguration
            }
        })
        bootstrap.addBundle(new AssetsBundle('/assets/ui', '/', 'index.html', 'ui'))
    }

    @Override
    public void run(StrengthsConfiguration configuration, Environment environment) {

        final DBIFactory factory = new DBIFactory()
        final DBI jdbi = factory.build(environment, configuration.dataSourceFactory, "db")

        final PersonDao personDao = jdbi.onDemand(PersonDao)
        final StrengthDao strengthDao = jdbi.onDemand(StrengthDao)
        final PersonStrengthDao personStrengthDao = jdbi.onDemand(PersonStrengthDao)

        final StrengthsModule strengthsModule = new StrengthsModule(strengthDao)
        final PeopleModule peopleModule = new PeopleModule(personDao, personStrengthDao)

        environment.servlets().addFilter("CORSFilter", CrossOriginFilter)
                .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType), true, "/*")
        environment.jersey().register(new StrengthsResource(strengthsModule))
        environment.jersey().register(new PeopleResource(peopleModule))
    }

}
