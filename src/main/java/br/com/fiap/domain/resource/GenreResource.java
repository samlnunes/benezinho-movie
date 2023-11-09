package br.com.fiap.domain.resource;

import br.com.fiap.Main;
import br.com.fiap.domain.dto.GenreDTO;
import br.com.fiap.domain.entity.Genre;
import br.com.fiap.domain.service.GenreService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.Objects;

@Path("/genre")
public class GenreResource implements Resource<GenreDTO, Long> {
    @Context
    private UriInfo uriInfo;

    GenreService service = GenreService.build(Main.PERSISTENCE_UNIT);

    @GET
    @Override
    public Response findAll() {
        return Response.ok( service.findAll().stream().map( GenreDTO::of ).toList() ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Genre genre = service.findById( id );

        if (Objects.isNull( genre )) return Response.status( 404 ).build();

        return Response.ok( GenreDTO.of( genre ) ).build();
    }

    @POST
    @Override
    public Response persist(GenreDTO g) {
        var genre = GenreDTO.of( g );

        Genre persisted = service.persist( genre );

        if (Objects.isNull( persisted )) return Response.status( 400 ).build();

        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI uri = ub.path( String.valueOf( persisted.getId() ) ).build();

        return Response.created( uri ).entity( GenreDTO.of( persisted ) ).build();
    }
}
