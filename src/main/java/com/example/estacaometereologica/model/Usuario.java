package com.example.estacaometereologica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Entidade que representa um Usuário comum no sistema.
 * Mapeia diretamente para a tabela 'usuarios' no banco de dados.
 */
@Entity
@Table(name = "usuarios")
@Getter // Lombok: Gera automaticamente os métodos getter para todos os campos
@Setter // Lombok: Gera automaticamente os métodos setter para todos os campos
public class Usuario {

    // Identificador único da entidade (Chave Primária)
    // GenerationType.IDENTITY repassa a responsabilidade do auto-incremento para o banco de dados (ideal para o Supabase/PostgreSQL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome completo do usuário. É obrigatório (not null) e tem limite de 100 caracteres
    @Column(nullable = false, length = 100)
    private String nome;

    // Email de acesso. Obrigatório, não permite duplicatas no banco (unique) e tem limite de 150 caracteres
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    // Senha do usuário (armazenada de forma criptografada). Obrigatória.
    @Column(nullable = false)
    private String senha;

    // Perfil de acesso do usuário comum.
    // updatable = false impede que este valor seja alterado via comandos de UPDATE do JPA.
    @Column(nullable = false, updatable = false)
    private String role = "ROLE_USER";

    // Define se o usuário está ativo ou desativado no sistema. Padrão é true (ativo).
    @Column(nullable = false)
    private boolean ativo = true;

    // Registra o momento exato em que o usuário foi cadastrado.
    // updatable = false garante que a data de criação original nunca seja modificada.
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    // Construtor padrão obrigatório para o Hibernate funcionar corretamente
    public Usuario() {}

    // Executado automaticamente antes do JPA realizar o INSERT no Supabase
    // Garante o preenchimento automático do campo 'criadoEm' com o horário do servidor
    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
    }
}