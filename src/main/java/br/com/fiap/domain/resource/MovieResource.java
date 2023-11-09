package br.com.fiap.domain.resource;

import br.com.fiap.Main;
import br.com.fiap.domain.dto.MovieDTO;
import br.com.fiap.domain.entity.Movie;
import br.com.fiap.domain.service.MovieService;
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

@Path("/movie")
public class MovieResource implements Resource<MovieDTO, Long> {
    @Context
    private UriInfo uriInfo;

    MovieService service = MovieService.build( Main.PERSISTENCE_UNIT );

    @GET
    @Override
    public Response findAll() {
        return Response.ok( service.findAll().stream().map( MovieDTO::of ).toList() ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Movie movie = service.findById( id );

        if (Objects.isNull( movie )) return Response.status( 404 ).build();

        return Response.ok( MovieDTO.of( movie ) ).build();
    }

    @POST
    @Override
    public Response persist(MovieDTO m) {
        var movie = MovieDTO.of( m );

        Movie persisted = service.persist( movie );

        if (Objects.isNull( persisted )) return Response.status( 400 ).build();

        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI uri = ub.path( String.valueOf( persisted.getId() ) ).build();

        return Response.created( uri ).entity( MovieDTO.of( persisted ) ).build();
    }
}
