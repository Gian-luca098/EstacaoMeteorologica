package com.example.estacaometereologica.service;


import com.example.estacaometereologica.dto.AdmRequestDTO;
import com.example.estacaometereologica.dto.AdmResponseDTO;
import com.example.estacaometereologica.model.Adm;
import com.example.estacaometereologica.repository.AdmRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Configuration
@EnableWebSecurity
public class AdmService {

    private final AdmRepository admRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdmService(AdmRepository admRepository, BCryptPasswordEncoder passwordEncoder) {
        this.admRepository = admRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Define o BCrypt como padrão do sistema
    }



    @Transactional
    public AdmResponseDTO cadastrar(AdmRequestDTO dto) {
        if (admRepository.findByLogin(dto.login()).isPresent()) {
            throw new IllegalArgumentException("Este login já está em uso.");
        }

        Adm adm = new Adm();
        adm.setLogin(dto.login());

        String senhaCriptografada = passwordEncoder.encode(dto.senha());
        adm.setSenha(senhaCriptografada);

        Adm admSalvo = admRepository.save(adm);

        // 💡 Veja como o retorno ficou limpo enviando apenas o objeto do banco
        return new AdmResponseDTO(admSalvo);
    }
}
