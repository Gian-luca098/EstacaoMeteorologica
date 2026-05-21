package com.example.estacaometereologica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Entidade que representa um Sensor da estação meteorológica.
 * Mapeia diretamente para a tabela 'sensores' no banco de dados.
 */
@Entity
@Table(name = "sensores")
@Getter // Lombok: Gera automaticamente os métodos getter para todos os campos
@Setter // Lombok: Gera automaticamente os métodos setter para todos os campos
public class Sensor {

    // Identificador único do sensor (Chave Primária) com auto-incremento gerenciado pelo banco
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome identificador do sensor. Obrigatório, com limite de 100 caracteres
    @Column(nullable = false, length = 100)
    private String nome;

    // Tipo do sensor (ex: TEMPERATURA, UMIDADE, PRESSAO). Obrigatório, com limite de 50 caracteres
    @Column(nullable = false, length = 50)
    private String tipo;

    // Texto descritivo sobre o sensor (onde está instalado, modelo, etc.). Obrigatório.
    @Column(nullable = false)
    private String descricao;

    // Define se o sensor está ativo e enviando leituras. Padrão é true (ativo).
    @Column(nullable = false)
    private boolean ativo = true;

    // Registra quando o sensor foi cadastrado pela primeira vez.
    // updatable = false garante que a data de criação original nunca seja alterada.
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    // Registra a data e hora da última modificação feita nos dados deste sensor.
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    // Construtor padrão obrigatório para o funcionamento do Hibernate
    public Sensor() {}

    /**
     * Método de callback do JPA executado automaticamente ANTES do INSERT.
     * Define a data atual do servidor tanto para a criação quanto para a primeira atualização.
     */
    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();
    }

    /**
     * Método de callback do JPA executado automaticamente ANTES de qualquer UPDATE.
     * Garante que o campo 'atualizadoEm' sempre reflita o momento exato da última alteração.
     */
    @PreUpdate
    protected void onUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
}