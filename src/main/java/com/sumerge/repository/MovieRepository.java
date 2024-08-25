package com.sumerge.repository;

import com.sumerge.model.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class MovieRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void createMovie(Movie movie) {
        entityManager.persist(movie);
    }

    @Transactional
    public void updateMovie(Movie movie) {
        entityManager.merge(movie);
    }

    @Transactional
    public void deleteMovieById(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie != null) {
            entityManager.remove(movie);
        }
    }

    @Transactional
    public Movie getMovieById(Long id) {
        return entityManager.find(Movie.class, id);
    }

    @Transactional
    public List<Movie> getMovies() {
        return entityManager.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }

    @Transactional
    public int getMoviesSize(){
        return entityManager.createQuery("SELECT m FROM Movie m", Movie.class).getResultList().size();
    }
}
