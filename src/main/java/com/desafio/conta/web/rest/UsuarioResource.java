package com.desafio.conta.web.rest;

import com.desafio.conta.service.UsuarioService;
import com.desafio.conta.service.dto.UsuarioDTO;
import com.desafio.conta.service.dto.UsuarioListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioResource {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        usuarioService.salvar(usuarioDTO);
        return ResponseEntity.created(URI.create("/api/usuario")).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioListDTO> obterId(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.obterPorId(id), HttpStatus.OK);
    }

}
