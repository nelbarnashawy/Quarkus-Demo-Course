package com.sumerge.repository;

import com.sumerge.model.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MovieRepositoryTest {

    @Mock
    private EntityManager entityManager;

//    @Mock
//    private TypedQuery<Movie> typedQuery;

    @InjectMocks
    private MovieRepository movieRepository;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setId(1L);
        movie.setName("Test Movie");
    }

    @Test
    void testCreateMovie() {
        // ACT
        movieRepository.createMovie(movie);
        // ASSERT
        verify(entityManager, Mockito.times(1)).persist(movie);
        verify(entityManager).persist(movie);
    }

    @Test
    void testUpdateMovie() {
        // ACT
        movieRepository.updateMovie(movie);
        // ASSERT
        verify(entityManager, Mockito.times(1)).merge(movie);
        verify(entityManager).merge(movie);
    }

    @Test
    void testDeleteMovieById() {
        // ARRANGE
        Long id = 1L;
        when(entityManager.find(Movie.class, id)).thenReturn(movie);
        // ACT
        movieRepository.deleteMovieById(id);
        // ASSERT
        verify(entityManager, Mockito.times(1)).remove(movie);
        verify(entityManager).remove(movie);
    }

    @Test
    void testGetMovieById() {
        // ARRANGE
        Long id = 1L;
        when(entityManager.find(Movie.class, id)).thenReturn(movie);
        // ACT
        Movie result = movieRepository.getMovieById(id);
        // ASSERT
        verify(entityManager, Mockito.times(1)).find(Movie.class, id);
        verify(entityManager).find(Movie.class, id);
    }

//    @Test
//    void testGetMovies() {
//        // ARRANGE
//        Movie movie2 = new Movie();
//        movie2.setId(2L);
//        movie2.setName("Test Movie 2");
//        List<Movie> movies = List.of(movie, movie2);
//
//        TypedQuery<Movie> typedQuery = mock(TypedQuery.class);
//        when(typedQuery.getResultList()).thenReturn(movies);
//
//        when(entityManager.createQuery("SELECT m FROM Movie m", Movie.class)).thenReturn(typedQuery);
//        // ACT
//        List<Movie> result = movieRepository.getMovies();
//        // ASSERT
//        verify(entityManager, Mockito.times(1)).createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
//        verify(entityManager).createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
//        assertThat(result).isEqualTo(movies);
//    }
//
//    @Test
//    void testGetMoviesSize() {
//        // ARRANGE
//        Movie movie2 = new Movie();
//        movie2.setId(2L);
//        movie2.setName("Test Movie 2");
//        List<Movie> movies = new ArrayList<>();
//        movies.add(movie);
//        movies.add(movie2);
//
//        when(entityManager.createQuery("SELECT m FROM Movie m", Movie.class)).thenReturn(typedQuery);
//        when(typedQuery.getResultList()).thenReturn(movies);
//        // ACT
//        int result = movieRepository.getMoviesSize();
//        // ASSERT
//        verify(entityManager, Mockito.times(1)).createQuery("SELECT m FROM Movie m", Movie.class).getResultList().size();
//        verify(entityManager).createQuery("SELECT m FROM Movie m", Movie.class);
//        verify(typedQuery).getResultList();
//        assertThat(result).isEqualTo(2);
//    }
}