package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Genre;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class GenreRepository implements Repository<Genre, Long> {
    private static final AtomicReference<GenreRepository> instance = new AtomicReference<>();
    private final EntityManager manager;

    public GenreRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static GenreRepository build(EntityManager manager) {
        instance.compareAndSet(null, new GenreRepository(manager));
        return instance.get();
    }

    @Override
    public List findAll() {
        return manager.createQuery("From Genre").getResultList();
    }

    @Override
    public Genre findById(Long id) {
        return manager.find(Genre.class, id);
    }

    @Override
    public Genre persist(Genre genre) {
        manager.getTransaction().begin();
        manager.persist(genre);
        manager.getTransaction().commit();
        return genre;
    }
}
