package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Movie;
import br.com.fiap.domain.repository.MovieRepository;
import br.com.fiap.infra.database.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MovieService implements Service<Movie, Long> {
    private static final AtomicReference<GenreService> instance = new AtomicReference<>();

    private final MovieRepository repo;

    private MovieService(MovieRepository repo) {
        this.repo = repo;
    }

    public static MovieService build(String persistenceUnit) {

        EntityManagerFactory factory = EntityManagerFactoryProvider.of(persistenceUnit).provide();
        EntityManager manager = factory.createEntityManager();
        MovieRepository repo = MovieRepository.build(manager);

        instance.compareAndSet(null, new MovieRepository(repo));
        return instance.get();
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Movie findById(Long id) {
        return null;
    }

    @Override
    public Movie persist(Movie movie) {
        return null;
    }
}
