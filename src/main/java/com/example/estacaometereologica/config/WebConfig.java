package com.example.estacaometereologica.config;


import com.example.estacaometereologica.interceptors.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Aplica o nosso middleware em todas as rotas da API de meteorologia
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/meteorologia/**");
    }
}