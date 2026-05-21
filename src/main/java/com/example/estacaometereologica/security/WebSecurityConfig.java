package com.example.estacaometereologica.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Classe de configuração de segurança da aplicação.
 * Aqui são definidas as regras de controle de acesso aos endpoints (URLs).
 */
@Configuration // Indica ao Spring que esta é uma classe de configuração (contém definições de Beans)
@EnableWebSecurity // Ativa o suporte de segurança web do Spring Security no projeto
public class WebSecurityConfig {

    /**
     * Configura o filtro de segurança (Security Filter Chain).
     * Este Bean intercepta todas as requisições HTTP que chegam na aplicação.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita a proteção contra CSRF (Cross-Site Request Forgery).
                // É uma prática comum e segura em APIs REST/Stateless, já que não utilizam cookies/sessões para autenticação.
                .csrf(csrf -> csrf.disable())

                // Inicia a configuração das regras de autorização para as requisições HTTP
                .authorizeHttpRequests(auth -> auth
                        // Define que qualquer requisição (qualquer endpoint) está permitida
                        // sem a necessidade de autenticação (libera o acesso público temporariamente).
                        .anyRequest().permitAll()
                );

        // Constrói e retorna a cadeia de filtros configurada
        return http.build();
    }
}