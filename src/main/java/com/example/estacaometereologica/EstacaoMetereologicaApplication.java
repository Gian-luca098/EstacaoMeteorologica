package com.example.estacaometereologica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal que inicializa a aplicação Spring Boot.
 */
@SpringBootApplication
// Esta é a anotação central do Spring Boot. Ela combina três anotações em uma só:
// 1. @Configuration: Permite registrar beans adicionais no contexto.
// 2. @EnableAutoConfiguration: Diz ao Spring Boot para começar a adicionar beans com base nas dependências do pom.xml (ex: configura o Hibernate automaticamente ao detectar o driver do banco).
// 3. @ComponentScan: Diz ao Spring para procurar por outros componentes, serviços, controllers e repositórios a partir DESTE pacote (com.example.estacaometereologica) para baixo.
public class EstacaoMetereologicaApplication {

    /**
     * Método main padrão do Java. É o ponto de entrada (entry point) do programa.
     */
    public static void main(String[] args) {
        // Inicializa o contexto do Spring, ativa o servidor embutido (Tomcat) e coloca a aplicação para rodar.
        SpringApplication.run(EstacaoMetereologicaApplication.class, args);
    }

}