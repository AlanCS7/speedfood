package io.github.alancs7.speedfood.api.mapper;

import io.github.alancs7.speedfood.api.model.dto.PermissaoDto;
import io.github.alancs7.speedfood.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PermissaoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoDto toDto(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoDto.class);
    }

    public List<PermissaoDto> toCollectionDto(Set<Permissao> permissoes) {
        return permissoes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
