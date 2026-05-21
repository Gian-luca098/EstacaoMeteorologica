package com.example.estacaometereologica.service;


import com.example.estacaometereologica.model.SensorData;
import com.example.estacaometereologica.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public SensorData salvarLeitura(SensorData dados) {
        // Validações de segurança no Back-end
        if (dados.getIdDispositivo() == null || dados.getIdDispositivo().isEmpty()) {
            throw new IllegalArgumentException("O ID do dispositivo é obrigatório.");
        }
        if (dados.getUmidade() < 0 || dados.getUmidade() > 100) {
            throw new IllegalArgumentException("Umidade fora dos padrões (0-100%).");
        }
        if (dados.getTemperatura() < -40 || dados.getTemperatura() > 80) {
            throw new IllegalArgumentException("Temperatura fora dos limites operacionais.");
        }
        if (dados.getPressao() < 300 || dados.getPressao() > 1100) {
            throw new IllegalArgumentException("Pressão atmosférica inválida.");
        }

        return sensorRepository.save(dados);
    }

    public List<SensorData> buscarHistorico() {
        return sensorRepository.findAll();
    }

    public void deletarLeitura(Long id) {
        if (!sensorRepository.existsById(id)) {
            throw new IllegalArgumentException("Registro não encontrado.");
        }
        sensorRepository.deleteById(id);
    }
}