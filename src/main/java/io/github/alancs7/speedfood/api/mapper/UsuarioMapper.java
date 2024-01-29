package io.github.alancs7.speedfood.api.mapper;

import io.github.alancs7.speedfood.api.model.dto.UsuarioDto;
import io.github.alancs7.speedfood.api.model.input.UsuarioComSenhaInput;
import io.github.alancs7.speedfood.api.model.input.UsuarioInput;
import io.github.alancs7.speedfood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDto toDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    public List<UsuarioDto> toCollectionDto(Collection<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Usuario toDomainObject(UsuarioComSenhaInput usuarioComSenhaInput) {
        return modelMapper.map(usuarioComSenhaInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInput usuarioInput, Usuario usuario) {
        modelMapper.map(usuarioInput, usuario);
    }
}
