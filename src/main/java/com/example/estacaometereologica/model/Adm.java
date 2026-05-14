package com.example.estacaometereologica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@Table(name = "administradores")
@Getter
@Setter
public class Adm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, updatable = false)
    private String role = "ROLE_ADM";

    @Column(nullable = false)
    private boolean ativo = true;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    public Adm() {}

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
    }
}

