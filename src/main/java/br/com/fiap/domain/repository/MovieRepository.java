package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Movie;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MovieRepository implements Repository<Movie, Long> {
    private static final AtomicReference<MovieRepository> instance = new AtomicReference<>();
    private final EntityManager manager;

    public MovieRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static MovieRepository build(EntityManager manager) {
        instance.compareAndSet(null, new MovieRepository(manager));
        return instance.get();
    }

    @Override
    public List findAll() {
        return manager.createQuery("From Movie").getResultList();
    }

    @Override
    public Movie findById(Long id) {
        return manager.find(Movie.class, id);
    }

    @Override
    public Movie persist(Movie movie) {
        manager.getTransaction().begin();
        manager.persist(movie);
        manager.getTransaction().commit();
        return movie;
    }
}
