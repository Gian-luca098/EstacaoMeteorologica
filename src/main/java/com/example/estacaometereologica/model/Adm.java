package com.example.estacaometereologica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Entidade que representa um Administrador no sistema.
 * Mapeia diretamente para a tabela 'administradores' no banco de dados.
 */
@Entity
@Table(name = "administradores")
@Getter // Lombok: Gera automaticamente os métodos getter para todos os campos
@Setter // Lombok: Gera automaticamente os métodos setter para todos os campos
public class Adm {

    // Identificador único da entidade (Chave Primária)
    // GenerationType.IDENTITY indica que o banco de dados lidará com o auto-incremento (ex: SERIAL no PostgreSQL, AUTO_INCREMENT no MySQL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome de usuário para login. É obrigatório (not null), único no banco e tem limite de 150 caracteres
    @Column(nullable = false, unique = true, length = 150)
    private String login;

    // Senha do administrador (geralmente armazenada como um hash criptografado). Obrigatória.
    @Column(nullable = false)
    private String senha;

    // Perfil de acesso/permissão do usuário.
    // updatable = false garante que esse valor não mude caso um comando de UPDATE seja disparado na entidade.
    @Column(nullable = false, updatable = false)
    private String role = "ROLE_ADM";

    // Flag para ativação/desativação lógica do usuário. Por padrão, inicia como ativo (true).
    @Column(nullable = false)
    private boolean ativo = true;

    // Registra a data e hora em que o registro foi criado.
    // name = "criado_em" define explicitamente o nome da coluna em snake_case no banco de dados.
    // updatable = false impede que a data de criação seja alterada em atualizações futuras.
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    // Construtor padrão (vazio) exigido pela especificação JPA
    public Adm() {}

    /**
     * Método de callback do JPA.
     * É executado automaticamente uma única vez, logo antes da entidade ser persistida (inserida) no banco de dados.
     * Garante que o campo 'criadoEm' sempre receba a data/hora atual do servidor.
     */
    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
    }
}