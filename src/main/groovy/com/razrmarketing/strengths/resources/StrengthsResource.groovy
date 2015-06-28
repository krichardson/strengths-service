package com.razrmarketing.strengths.resources

import com.codahale.metrics.annotation.Timed
import com.razrmarketing.strengths.api.Strength
import com.razrmarketing.strengths.module.StrengthsModule
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import groovy.util.logging.Slf4j
import io.dropwizard.jersey.params.LongParam

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/strengths")
@Api("/strengths")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
class StrengthsResource {

    private final StrengthsModule strengthsModule

    StrengthsResource(StrengthsModule strengthsModule) {
        this.strengthsModule = strengthsModule
    }

    @GET
    @ApiOperation("Get the full list of strengths")
    @Timed
    public List<Strength> list() {
        return strengthsModule.listStrengths()
    }

    @GET
    @Path('/{id}')
    @ApiOperation("Get a single strength")
    @Timed
    public Strength getStrength(@PathParam('id') LongParam id) {
        return strengthsModule.getStrength(id.get())
    }

}
