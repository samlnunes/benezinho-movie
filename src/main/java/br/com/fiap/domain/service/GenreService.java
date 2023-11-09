package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Genre;
import br.com.fiap.domain.repository.GenreRepository;
import br.com.fiap.infra.database.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class GenreService implements Service<Genre, Long> {
    private static final AtomicReference<GenreService> instance = new AtomicReference<>();

    private final GenreRepository repo;

    private GenreService(GenreRepository repo) {
        this.repo = repo;
    }

    public static GenreService build(String persistenceUnit) {

        EntityManagerFactory factory = EntityManagerFactoryProvider.of(persistenceUnit).provide();
        EntityManager manager = factory.createEntityManager();
        GenreRepository repo = GenreRepository.build(manager);

        instance.compareAndSet(null, new GenreRepository(repo));
        return instance.get();
    }

    @Override
    public List findAll() {
        return repo.findAll();
    }

    @Override
    public Genre findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Genre persist(Genre genre) {
        return repo.persist(genre);
    }
}
