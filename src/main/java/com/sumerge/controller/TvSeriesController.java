package com.sumerge.controller;

import com.sumerge.model.Episode;
import com.sumerge.model.TvSerie;
import com.sumerge.proxy.EpisodesProxy;
import com.sumerge.proxy.TvSeriesProxy;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@Path("/tvseries")
public class TvSeriesController {

    @RestClient
    TvSeriesProxy tvSeriesProxy;

    @RestClient
    EpisodesProxy episodesProxy;

    private List<TvSerie> tvSeries = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTvSeriesAndEpisodes(@QueryParam("name") String name) {
        TvSerie tvSerie = tvSeriesProxy.getShow(name);
        List<Episode> episodes = episodesProxy.getEpisodes(tvSerie.getId());
        return Response.ok(episodes).build();
    }


}
