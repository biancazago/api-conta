package com.desafio.conta.dto;

import com.desafio.conta.enumeration.TipoUsuarioEnum;
import com.desafio.conta.provider.UsuarioGroupSequenceProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
