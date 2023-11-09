package br.com.fiap.domain.resource;

import br.com.fiap.Main;
import br.com.fiap.domain.entity.Genre;
import br.com.fiap.domain.service.GenreService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/genre")
public class GenreResource implements Resource<Genre, Long> {
    @Context
    private UriInfo uriInfo;

    GenreService service = GenreService.build(Main.PERSISTENCE_UNIT);

    @GET
    @Override
    public Response findAll() {
        return Response.ok(service.findAll().stream().map().toList()).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(Long id) {
        return null;
    }

    @POST
    @Override
    public Response persist(Genre genre) {
        return null;
    }
}
