package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.mapper.UsuarioMapper;
import io.github.alancs7.speedfood.api.model.dto.UsuarioDto;
import io.github.alancs7.speedfood.api.model.input.SenhaInput;
import io.github.alancs7.speedfood.api.model.input.UsuarioComSenhaInput;
import io.github.alancs7.speedfood.api.model.input.UsuarioInput;
import io.github.alancs7.speedfood.api.openapi.controller.UsuarioControllerOpenApi;
import io.github.alancs7.speedfood.domain.model.Usuario;
import io.github.alancs7.speedfood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper mapper;

    @GetMapping
    public List<UsuarioDto> listar() {
        return mapper.toCollectionDto(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public UsuarioDto buscar(@PathVariable Long id) {
        return mapper.toDto(usuarioService.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenhaInput) {
        Usuario usuario = mapper.toDomainObject(usuarioComSenhaInput);

        return mapper.toDto(usuarioService.salvar(usuario));
    }

    @PutMapping("/{id}")
    public UsuarioDto atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuarioAtual = usuarioService.buscarOuFalhar(id);

        mapper.copyToDomainObject(usuarioInput, usuarioAtual);

        return mapper.toDto(usuarioService.salvar(usuarioAtual));
    }

    @PutMapping("/{id}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long id, @RequestBody @Valid SenhaInput senhaInput) {
        usuarioService.alterarSenha(id, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
    }

}
