package com.desafio.conta.web.rest;

import com.desafio.conta.service.TransferirService;
import com.desafio.conta.service.dto.DadosTransferenciaDTO;
import com.desafio.conta.service.dto.MensagemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transferir")
public class TransferirResource {

    private final TransferirService transferirService;

    @PutMapping
    public ResponseEntity<MensagemDTO> transferir(@RequestBody @Valid DadosTransferenciaDTO transferenciaDTO) {
        return new ResponseEntity<>(transferirService.transferir(transferenciaDTO), HttpStatus.OK);
    }

}
