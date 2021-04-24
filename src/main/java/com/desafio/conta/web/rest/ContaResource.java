package com.desafio.conta.web.rest;

import com.desafio.conta.service.ContaService;
import com.desafio.conta.service.dto.DadosContaDTO;
import com.desafio.conta.service.dto.ValorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/conta")
public class ContaResource {

    private final ContaService contaService;

    @PatchMapping("/{id}")
    public ResponseEntity<Void> adicionarDinheiroConta(@PathVariable Long id, @RequestBody ValorDTO valorDTO) {
        contaService.adicionarDinheiroConta(id, valorDTO.getValor());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosContaDTO> obterContaUsuario(@PathVariable Long id) {
        return new ResponseEntity<>(contaService.obterContaUsuario(id), HttpStatus.OK);
    }


}
