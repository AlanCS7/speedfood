package io.github.alancs7.speedfood.domain.model;

import io.github.alancs7.speedfood.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Valid
    @ConvertGroup(from = Default.class, to =  Groups.EstadoId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

}
