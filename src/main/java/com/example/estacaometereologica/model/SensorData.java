package com.example.estacaometereologica.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "historico_sensores")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_dispositivo", nullable = false)
    private String idDispositivo;

    @Column(nullable = false)
    private Double temperatura;

    @Column(nullable = false)
    private Double umidade;

    @Column(nullable = false)
    private Double pressao;

    @Column(name = "data_coleta", nullable = false, updatable = false)
    private OffsetDateTime dataColeta = OffsetDateTime.now();

    // Getters e Setters
    public Long getId() { return id; }
    public String getIdDispositivo() { return idDispositivo; }
    public void setIdDispositivo(String idDispositivo) { this.idDispositivo = idDispositivo; }
    public Double getTemperatura() { return temperatura; }
    public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }
    public Double getUmidade() { return umidade; }
    public void setUmidade(Double umidade) { this.umidade = umidade; }
    public Double getPressao() { return pressao; }
    public void setPressao(Double pressao) { this.pressao = pressao; }
    public OffsetDateTime getDataColeta() { return dataColeta; }
}