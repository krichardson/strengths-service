package com.razrmarketing.strengths

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration

import javax.validation.Valid
import javax.validation.constraints.NotNull

class StrengthsConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    DataSourceFactory dataSourceFactory = new DataSourceFactory()

    @Valid
    @NotNull
    @JsonProperty("swagger")
    SwaggerBundleConfiguration swaggerBundleConfiguration

}
