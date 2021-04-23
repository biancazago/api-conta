package com.desafio.conta.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegraNegocioException extends RuntimeException {

    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}