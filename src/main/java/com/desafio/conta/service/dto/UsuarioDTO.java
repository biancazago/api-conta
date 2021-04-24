package com.desafio.conta.service.dto;

import com.desafio.conta.service.enumeration.TipoUsuarioEnum;
import com.desafio.conta.service.interfaces.CnpjGroup;
import com.desafio.conta.service.interfaces.CpfGroup;
import com.desafio.conta.service.provider.UsuarioGroupSequenceProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@GroupSequenceProvider(UsuarioGroupSequenceProvider.class)
public class UsuarioDTO implements Serializable {

    @NotNull(message = "Nome é campo obrigatório")
    @NotBlank(message = "Nome deve ser preenchido")
    private String nome;

    private TipoUsuarioEnum tipoUsuario;

    @CPF(groups = CpfGroup.class)
    @CNPJ(groups = CnpjGroup.class)
    @NotNull(message = "CPF/CNPJ é campo obrigatório")
    @NotBlank(message = "CPF/CNPJ deve ser preenchido")
    private String cpfCnpj;

    @Email
    @NotNull(message = "Email é campo obrigatório")
    @NotBlank(message = "Email deve ser preenchido")
    private String email;

    @NotNull(message = "Senha é campo obrigatório")
    @NotBlank(message = "Senha deve ser preenchido")
    private String senha;

}
