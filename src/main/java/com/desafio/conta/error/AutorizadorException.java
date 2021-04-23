package com.desafio.conta.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AutorizadorException extends RuntimeException {

    public AutorizadorException(String mensagem) {
        super(mensagem);
    }
}