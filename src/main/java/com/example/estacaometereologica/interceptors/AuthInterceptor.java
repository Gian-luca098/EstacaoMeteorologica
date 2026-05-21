package com.example.estacaometereologica.interceptors;


import com.example.estacaometereologica.model.Role;
import com.example.estacaometereologica.model.UserProfile;
import com.example.estacaometereologica.repository.ProfileRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;
import java.util.UUID;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String method = request.getMethod();

        // 1. Rota do ESP32 (POST) fica liberada sem token de login para facilitar
        if (uri.equals("/api/meteorologia") && method.equals("POST")) {
            return true;
        }

        // 2. Verifica se o Header Authorization foi enviado
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token de acesso faltando.");
            return false;
        }

        // 💡 NOTA: Aqui você usaria uma biblioteca JWT (como io.jsonwebtoken) para descriptografar
        // o token vindo do Supabase Auth e extrair o ID do usuário (User ID).
        // Abaixo simulamos que pegamos o ID do usuário 'sub' do token:
        String userIdSimuladoDoToken = "coloque-aqui-o-id-extraido-do-jwt";
        UUID userUUID = UUID.fromString(userIdSimuladoDoToken);

        // 3. Busca o perfil do usuário na tabela do banco
        Optional<UserProfile> perfilOpt = profileRepository.findById(userUUID);
        if (perfilOpt.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Perfil nao encontrado no sistema.");
            return false;
        }

        UserProfile perfil = perfilOpt.get();

        // 4. Aplica as travas de rota baseadas na Role
        if (uri.contains("/api/meteorologia/") && method.equals("DELETE")) {
            if (perfil.getRole() != Role.ADMIN) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Acesso negado. Apenas administradores.");
                return false;
            }
        }

        return true; // Passou em todas as validações, segue viagem para o Controller!
    }
}