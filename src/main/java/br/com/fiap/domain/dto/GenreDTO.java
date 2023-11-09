package br.com.fiap.domain.dto;

import br.com.fiap.Main;
import br.com.fiap.domain.entity.Genre;
import br.com.fiap.domain.service.GenreService;

import java.util.Objects;

public record GenreDTO(Long id, String name) {
    private static GenreService service = GenreService.build(Main.PERSISTENCE_UNIT);

    public static Genre of(GenreDTO dto) {
        if (Objects.isNull(dto)) return null;

        if (Objects.nonNull(dto.id)) return service.findById(dto.id);

        Genre genre = new Genre();
        genre.setName(dto.name);

        return genre;
    }

    public static GenreDTO of(Genre g) {
        if (Objects.isNull(g)) return null;
        return new GenreDTO(g.getId(), g.getName());
    }
}
