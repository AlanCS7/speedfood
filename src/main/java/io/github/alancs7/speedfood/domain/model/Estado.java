package io.github.alancs7.speedfood.domain.model;

import io.github.alancs7.speedfood.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Estado {

    @NotNull(groups = Groups.EstadoId.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

}
