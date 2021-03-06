package com.desafio.conta.service.feign;

import com.desafio.conta.service.dto.MensagemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "tranferencia", url = "${autorizador.url}")
public interface MockAutorizadorFeignClient {


    @GetMapping(value = "/8fafdd68-a090-496f-8c9a-3442cf30dae6-tdsadasdasdsdadadasdasesre")
    MensagemDTO autorizadorTrasferenciaErro();


    @GetMapping(value = "/8fafdd68-a090-496f-8c9a-3442cf30dae6")
    MensagemDTO autorizadorTrasferencia();

    @GetMapping(value = "/b19f7b9f-9cbf-4fc6-ad22-dc30601aec04")
    MensagemDTO verificarConclusaoTransferencia();


}

