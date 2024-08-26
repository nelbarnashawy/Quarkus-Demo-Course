package com.sumerge.proxy;

import com.sumerge.model.Episode;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

//https://api.tvmaze.com/shows/82/episodes


@Path("/shows")
@RegisterRestClient(baseUri = "https://api.tvmaze.com")
public interface EpisodesProxy {

    @GET
    @Path("/{id}/episodes")
    List<Episode> getEpisodes(@PathParam("id") Long id);

}
