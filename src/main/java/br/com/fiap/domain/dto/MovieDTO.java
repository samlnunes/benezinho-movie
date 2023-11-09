package br.com.fiap.domain.dto;

import br.com.fiap.Main;
import br.com.fiap.domain.entity.Genre;
import br.com.fiap.domain.entity.Movie;
import br.com.fiap.domain.service.MovieService;

import java.util.Objects;
import java.util.Set;

public record MovieDTO(Long id, boolean adult, String backdropPath, Set<Genre> genres, String originalLanguage, String originalTitle, String overview, Double popularity, String posterPath, String releaseDate, String title, boolean video, double voteAverage, Integer voteCount) {
    private static MovieService service = MovieService.build(Main.PERSISTENCE_UNIT);

    public static Movie of(MovieDTO dto) {
        if (Objects.isNull(dto)) return null;

        if (Objects.nonNull(dto.id)) return service.findById(dto.id);

        Movie movie = new Movie();
        movie.setAdult(dto.adult);
        movie.setBackdropPath(dto.backdropPath);
        movie.setGenres(dto.genres);
        movie.setOriginalLanguage(dto.originalLanguage);
        movie.setOriginalTitle(dto.originalTitle);
        movie.setOverview(dto.overview);
        movie.setPopularity(dto.popularity);
        movie.setPosterPath(dto.posterPath);
        movie.setReleaseDate(dto.releaseDate);
        movie.setTitle(dto.title);
        movie.setVideo(dto.video);
        movie.setVoteAverage(dto.voteAverage);
        movie.setVoteCount(dto.voteCount);

        return movie;
    }
}
