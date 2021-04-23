package com.desafio.conta.provider;

import com.desafio.conta.dto.UsuarioDTO;
import com.desafio.conta.interfaces.CnpjGroup;
import com.desafio.conta.interfaces.CpfGroup;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class UsuarioGroupSequenceProvider implements DefaultGroupSequenceProvider<UsuarioDTO> {

    public List<Class<?>> getValidationGroups(UsuarioDTO dto) {
        List<Class<?>> groups = new ArrayList<>();

        groups.add(UsuarioDTO.class);

        if (dto != null) {
            dto.setCpfCnpj(dto.getCpfCnpj().replaceAll("([.\\-\\/])", ""));
            if (dto.getCpfCnpj().length() < 14) {
                groups.add(CpfGroup.class);
            } else {
                groups.add(CnpjGroup.class);
            }
        }

        return groups;
    }

}