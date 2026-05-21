package com.example.estacaometereologica.controller;

import com.example.estacaometereologica.model.SensorData;
import com.example.estacaometereologica.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



    @RestController
    @RequestMapping("/api/meteorologia")
    @CrossOrigin(origins = "*") // Permite conexões externas
    public class SensorController {

        @Autowired
        private SensorService sensorService;

        // O ESP32 envia dados para cá
        @PostMapping
        public ResponseEntity<?> criarLeitura(@RequestBody SensorData dados) {
            try {
                SensorData resultado = sensorService.salvarLeitura(dados);
                return new ResponseEntity<>(resultado, HttpStatus.CREATED);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        // Usuários comuns e Admins cadastrados podem ler aqui
        @GetMapping("/historico")
        public ResponseEntity<List<SensorData>> listarHistorico() {
            return ResponseEntity.ok(sensorService.buscarHistorico());
        }

        // Apenas Admins podem deletar registros errados
        @DeleteMapping("/{id}")
        public ResponseEntity<?> removerLeitura(@PathVariable Long id) {
            try {
                sensorService.deletarLeitura(id);
                return ResponseEntity.ok().body("{\"mensagem\": \"Registro deletado com sucesso.\"}");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    }


