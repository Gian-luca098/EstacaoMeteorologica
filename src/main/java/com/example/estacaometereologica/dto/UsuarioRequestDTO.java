package com.example.estacaometereologica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) utilizado para receber os dados ao cadastrar ou atualizar um Usuário.
 * Sendo um 'record', ele garante imutabilidade e encapsulamento dos dados que chegam do cliente (frontend/Postman).
 */
public record UsuarioRequestDTO(
        // Valida que o nome não pode ser nulo ou vazio e define um limite mínimo e máximo de caracteres
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        String nome,

        // Valida que o e-mail não pode ser vazio e precisa estar no formato correto (exemplo@email.com)
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Insira um e-mail válido")
        String email,

        // Valida que a senha não pode ser vazia e impõe um tamanho mínimo de segurança
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha
) {}