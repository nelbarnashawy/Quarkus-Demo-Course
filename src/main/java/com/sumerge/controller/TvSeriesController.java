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
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
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
        TvSerie tvSerie = getTvSeries(name);
        List<Episode> episodes = getEpisodes(tvSerie.getId());
        return Response.ok(episodes).build();
    }

    @Fallback(fallbackMethod = "fallbackGetTvSeries")
    public TvSerie getTvSeries(String name) {
        return tvSeriesProxy.getShow(name);
    }

    private TvSerie fallbackGetTvSeries(String name) {
        TvSerie fallbackSerie = new TvSerie();
        fallbackSerie.setId(1L);
        return fallbackSerie;
    }

    @Timeout(10) //Will timeout and go to fallback method since the API doesn't respond in 0.01 seconds
//    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.75, delay = 4000, successThreshold = 2)
//    @Retry(maxRetries = 4)
    @Fallback(fallbackMethod = "fallbackGetEpisodes")
    public List<Episode> getEpisodes(Long id) {
        return episodesProxy.getEpisodes(id);
    }

    private List<Episode> fallbackGetEpisodes(Long id) {
        return new ArrayList<>();
    }


}
