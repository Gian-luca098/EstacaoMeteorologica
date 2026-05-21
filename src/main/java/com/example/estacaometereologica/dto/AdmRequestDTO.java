package com.example.estacaometereologica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) utilizado para receber os dados de criação/autenticação de um Administrador.
 * Utiliza o recurso 'record' do Java (introduzido no Java 14/16), que cria uma classe imutável
 * com construtor, getters, equals, hashCode e toString gerados automaticamente.
 */
public record AdmRequestDTO(

        // Valida se o campo não está nulo e nem composto apenas por espaços em branco.
        // Garante também que o texto inserido siga o formato padrão de um e-mail válido.
        @NotBlank(message = "O login do ADM é obrigatório")
        @Email(message = "O login deve ser um e-mail válido")
        String login,

        // Valida se a senha foi preenchida (não nula/vazia) e impõe um limite mínimo de 6 caracteres.
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha do ADM deve ter no mínimo 6 caracteres")
        String senha
) {
}