package com.example.estacaometereologica.dto;

import com.example.estacaometereologica.model.Adm;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) utilizado para retornar os dados de um Administrador nas respostas da API.
 * Evita expor diretamente a entidade de banco de dados (Adm) e oculta campos sensíveis, como a senha.
 */
public record AdmResponseDTO(
        Long id,
        String login,
        String role,
        boolean ativo,
        LocalDateTime criadoEm
) {

    /**
     * Construtor secundário que facilita a conversão de uma entidade 'Adm' para este DTO.
     * Captura os dados do objeto do banco de dados e repassa para o construtor principal do record.
     *
     * @param adm A entidade original vinda do banco de dados/camada de serviço.
     */
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