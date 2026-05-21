package com.example.estacaometereologica.dto;

import com.example.estacaometereologica.model.Usuario;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) utilizado para retornar os dados de um Usuário nas respostas da API.
 * Garante que dados sensíveis (como a senha) não sejam enviados de volta para o cliente.
 */
public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String role,
        boolean ativo,
        LocalDateTime criadoEm
) {

    /**
     * Construtor secundário para mapear diretamente uma entidade 'Usuario' para este DTO.
     * Facilita a conversão dos dados vindos do banco para o formato de resposta da API.
     *
     * @param usuario A entidade original vinda do banco de dados/camada de serviço.
     */
    public UsuarioResponseDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.isAtivo(),
                usuario.getCriadoEm()
        );
    }
}