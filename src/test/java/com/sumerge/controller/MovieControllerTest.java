//package com.sumerge.controller;
//
//import com.sumerge.dto.MovieDTO;
//import com.sumerge.model.Movie;
//import com.sumerge.repository.MovieRepository;
//import io.quarkus.test.InjectMock;
//import io.quarkus.test.junit.QuarkusTest;
//import jakarta.inject.Inject;
//import jakarta.ws.rs.core.Response;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mockito;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@QuarkusTest
//class MovieControllerTest {
//
//    @InjectMock
//    MovieRepository movieRepository;
//
//    @Inject
//    MovieController movieController;
//
//    private Movie movie;
//    private Movie movie2;
//    private MovieDTO movieDto;
//    private MovieDTO movieDto2;
//
//    @BeforeEach
//    void setUp() {
//        movie = new Movie();
//        movie2 = new Movie();
//        movie.setId(1L);
//        movie.setName("Test Movie");
//        movie2.setId(2L);
//        movie2.setName("Test Movie 2");
//        movieDto = new MovieDTO();
//        movieDto2 = new MovieDTO();
//        movieDto.setName("Test Movie");
//        movieDto2.setName("Test Movie 2");
//    }
//
//    @Test
//    void getAllMovies() {
//        // ARRANGE
//        List<Movie> movies = new ArrayList<>();
//        movies.add(movie);
//        movies.add(movie2);
//        Mockito.when(movieRepository.getMovies()).thenReturn(movies);
//        // ACT
//        Response response = movieController.getMovies();
//        // ASSERT
//        assertThat(response).isNotNull();
//        assertThat(response.getEntity()).isEqualTo(movies);
//        assertThat(response.getStatus()).isEqualTo(200);
//    }
//
//    @Test
//    void getMoviesSize() {
//        // ARRANGE
//        Mockito.when(movieRepository.getMoviesSize()).thenReturn(0);
//        // ACT
//        int size = movieController.getMoviesSize();
//        // ASSERT
//        assertThat(size).isNotNull();
//        assertThat(size).isEqualTo(0);
//    }
//
//    @Test
//    void createMovie() {
//        // ARRANGE
//        Movie movie3 = new Movie();
//        movie3.setId(3L);
//        movie3.setName("Test Movie 3");
//        MovieDTO movieDto3 = new MovieDTO();
//        movieDto3.setName("Test Movie 3");
//        List<MovieDTO> movies = new ArrayList<>();
//        movies.add(movieDto);
//        movies.add(movieDto2);
//        movies.add(movieDto3);
//        Mockito.doNothing().when(movieRepository).createMovie(ArgumentMatchers.any(MovieDTO.class));
//        Mockito.when(movieRepository.getMovies()).thenReturn(movies);
//        // ACT
//        Response response = movieController.createMovie(movie);
//        // ASSERT
//        assertThat(response).isNotNull();
//        assertThat(response.getStatus()).isEqualTo(201);
//        assertThat(response.getEntity()).isEqualTo(movies);
//    }
//
//    @Test
//    void updateAnExistingMovie() {
//        // ARRANGE
//        Long id = 1L;
//
//        Mockito.when(movieRepository.getMovieById(id)).thenReturn(movie);
//
//        Movie updatedMovie = new Movie();
//        updatedMovie.setId(1L);
//        updatedMovie.setName("Updated Movie");
//
//        List<Movie> movies = new ArrayList<>();
//        movies.add(updatedMovie);
//        movies.add(movie2);
//
//        Mockito.doNothing().when(movieRepository).updateMovie(ArgumentMatchers.any(Movie.class));
//        Mockito.when(movieRepository.getMovies()).thenReturn(movies);
//        // ACT
//        Response response = movieController.updateMovie("Updated Movie", id);
//        // ASSERT
//        assertThat(response).isNotNull();
//        assertThat(response.getStatus()).isEqualTo(200);
//        assertThat(response.getEntity()).isEqualTo(movies);
//    }
//
//    @Test
//    void updateANonExistingMovie() {
//        // ARRANGE
//        Long id = 5L;
//
//        Mockito.when(movieRepository.getMovieById(id)).thenReturn(null);
//
//        // ACT
//        Response response = movieController.updateMovie("Updated Movie", id);
//        // ASSERT
//        assertThat(response).isNotNull();
//        assertThat(response.getStatus()).isEqualTo(404);
//    }
//
//    @Test
//    void deleteAnExistingMovie() {
//        // ARRANGE
//        Long id = 1L;
//        List<Movie> movies = new ArrayList<>();
//        movies.add(movie2);
//        Mockito.when(movieRepository.getMovieById(id)).thenReturn(movie);
//        Mockito.doNothing().when(movieRepository).deleteMovieById(id);
//        Mockito.when(movieRepository.getMovies()).thenReturn(movies);
//        // ACT
//        Response response = movieController.deleteMovie(id);
//        // ASSERT
//        assertThat(response).isNotNull();
//        assertThat(response.getStatus()).isEqualTo(200);
//        assertThat(response.getEntity()).isEqualTo(movies);
//    }
//
//    @Test
//    void deleteANonExistingMovie() {
//        // ARRANGE
//        Long id = 5L;
//
//        Mockito.when(movieRepository.getMovieById(id)).thenReturn(null);
//
//        // ACT
//        Response response = movieController.deleteMovie(id);
//        // ASSERT
//        assertThat(response).isNotNull();
//        assertThat(response.getStatus()).isEqualTo(404);
//    }
//}