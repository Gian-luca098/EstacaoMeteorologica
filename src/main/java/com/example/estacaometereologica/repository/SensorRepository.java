package com.example.estacaometereologica.repository;


import com.example.estacaometereologica.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<SensorData, Long> {
    // Já vem com save(), findAll(), deleteById() nativos do Spring
}