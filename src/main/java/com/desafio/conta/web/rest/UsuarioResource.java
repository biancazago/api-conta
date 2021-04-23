package com.desafio.conta.web.rest;

import com.desafio.conta.service.UsuarioService;
import com.desafio.conta.service.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioResource {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        usuarioService.salvar(usuarioDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
