package com.razrmarketing.strengths.resources

import com.codahale.metrics.annotation.Timed
import com.razrmarketing.strengths.api.Person
import com.razrmarketing.strengths.module.PeopleModule
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import groovy.util.logging.Slf4j
import io.dropwizard.jersey.params.LongParam
import com.google.common.base.Optional

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/people")
@Api("/people")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
class PeopleResource {

    private final PeopleModule peopleModule

    PeopleResource(PeopleModule peopleModule) {
        this.peopleModule = peopleModule
    }

    @GET
    @ApiOperation("Get the full list of people optionally filtered by strength")
    @Timed
    public List<Person> list(@QueryParam('withStrength') LongParam strengthId) {
        if (strengthId?.get()) {
            return peopleModule.findPeopleWithStrength(strengthId.get())
        }
        return peopleModule.listPeople()
    }

    @GET
    @Path('/{id}')
    @ApiOperation("Get a specific person")
    @Timed
    public Person getPerson(@PathParam('id') LongParam id) {
        return peopleModule.getPerson(id.get())
    }

}
