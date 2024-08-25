package com.sumerge.proxy;

import com.sumerge.model.TvSerie;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

// https://api.tvmaze.com/singlesearch/shows?q=game%20of%20thrones
/*
baseUri in RegisterRestClient(baseUri = "") OR IN APPLICATION.PROPERTIES (package.class/mp-rest/uri= here  &
package.class/mp-rest/scope=javax.inject.Singleton -- Then main Path in the interface's @Path(" here ") -- Then the rest
of the path in the method's @Path(" here ") -- Then the query parameters in the method's @QueryParam(" same as query in
api here ")
*/


@Path("/singlesearch")
@Produces(MediaType.APPLICATION_JSON)
//@RegisterRestClient(baseUri = "https://api.tvmaze.com")
@RegisterRestClient
public interface TvSeriesProxy {

    @GET
    @Path("/shows")
    TvSerie getShow(@QueryParam("q") String name);
}
