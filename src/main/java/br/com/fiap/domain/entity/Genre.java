package br.com.fiap.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "TB_GENRE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NM_GENRE", columnNames = "NM_GENRE")
})
public class Genre {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_GENRE")
    @Column(name = "ID_GENRE")
    private Long id;

    @Column(name = "NM_GENRE")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}