package com.example.estacaometereologica.dto;

import com.example.estacaometereologica.model.Adm;

import java.time.LocalDateTime;

public record AdmResponseDTO(
        Long id,
        String login,
        String role,
        boolean ativo,
        LocalDateTime criadoEm
) {

    public AdmResponseDTO(Adm adm) {
        this(
                adm.getId(),
                adm.getLogin(),
                adm.getRole(),
                adm.isAtivo(),
                adm.getCriadoEm()
        );
    }
}

