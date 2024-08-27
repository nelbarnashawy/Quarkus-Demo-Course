package com.sumerge.controller;

import com.sumerge.model.Movie;
import com.sumerge.repository.MovieRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/movies")
@Tag(name = "Movie Controller", description = "CRUD operations for movies")
public class MovieController {

    @Inject
    @ConfigProperty(name = "config.message.inject", defaultValue = "Hello from controller! No message found in config")
    String helloMessage;

    private final MovieRepository movieRepository;

    @Inject
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GET
    @Path("/configInject")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return helloMessage;
    }

    @GET
    @Path("/configProvider")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessageByConfigProviderOptional(){
        Config config = ConfigProvider.getConfig();
        String message = config.getOptionalValue("config.message.provider", String.class)
                .orElse("Hello from controller! No message found in config");
        return message;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getMovies", summary = "Get all movies", description = "Get all movies from the database")
    @APIResponse(
            responseCode = "200", description = "Movies retrieved successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getMovies() {
        return Response.ok(movieRepository.getMovies()).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/size")
    @Operation(
            operationId = "getMovieSize",
            summary = "Get movies size",
            description = "Get the number of movies in the database"
    )
    @APIResponse(
            responseCode = "200",
            description = "Movies size retrieved successfully", content = @Content(mediaType = MediaType.TEXT_PLAIN)
    )
    public int getMoviesSize() {
        return movieRepository.getMoviesSize();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "createMovie", summary = "Create a movie",
            description = "Create a movie in the database"
    )
    @APIResponse(
            responseCode = "201",
            description = "Movie created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response createMovie(@RequestBody(
            description = "The movie to create",
            required = true,
            content = @Content(schema = @Schema(implementation = Movie.class))
    ) Movie movie) {
        movieRepository.createMovie(movie);
        return Response.status(Response.Status.CREATED).entity(movieRepository.getMovies()).build();
    }

    @PUT
    @Path("{id}/{updatedTitle}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "updateMovie", summary = "Update a movie",
            description = "Update a movie's title in the database"
    )
    @APIResponses(
            {
                    @APIResponse(
                            responseCode = "200",
                            description = "Movie updated successfully",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)
                    ),
                    @APIResponse(
                            responseCode = "404",
                            description = "Movie not found"
                    )
            }
    )
    public Response updateMovie(
            @Parameter(description = "Updated movie", required = true) @PathParam("updatedTitle") String updatedTitle,
            @Parameter(description = "ID of movie to be updated", required = true) @PathParam("id") Long id) {
        Movie movieToUpdate = movieRepository.getMovieById(id);
        if (movieToUpdate != null) {
            movieToUpdate.setName(updatedTitle);
            movieRepository.updateMovie(movieToUpdate);
            return Response.ok(movieRepository.getMovies()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "deleteMovie",
            summary = "Delete a movie",
            description = "Delete a movie from the database"
    )
    @APIResponses(
            {
                    @APIResponse(
                            responseCode = "200",
                            description = "Movie deleted successfully",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)
                    ),
                    @APIResponse(
                            responseCode = "404",
                            description = "Movie not found"
                    )
            }
    )
    public Response deleteMovie(@PathParam("id") Long id) {
        if (movieRepository.getMovieById(id) != null) {
            movieRepository.deleteMovieById(id);
            return Response.ok(movieRepository.getMovies()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
