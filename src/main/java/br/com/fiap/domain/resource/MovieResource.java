package br.com.fiap.domain.resource;

import br.com.fiap.domain.entity.Movie;
import jakarta.ws.rs.core.Response;

public class MovieResource implements Resource<Movie, Long> {
    @Override
    public Response findAll() {
        return null;
    }

    @Override
    public Response findById(Long id) {
        return null;
    }

    @Override
    public Response persist(Movie movie) {
        return null;
    }
}
