package com.example.estacaometereologica.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "dados_sensores")
@Getter
@Setter
public class DadoSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private String unidade; // °C, %, hPa

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataColeta;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    public DadoSensor() {}

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDateTime.now();
        if (this.dataColeta == null) {
            this.dataColeta = LocalDateTime.now();
        }
    }
}
