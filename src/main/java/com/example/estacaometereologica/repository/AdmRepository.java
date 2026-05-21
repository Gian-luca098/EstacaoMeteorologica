package com.example.estacaometereologica.repository;

import com.example.estacaometereologica.model.Adm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de Repositório para a entidade Adm.
 * Responsável por intermediar as operações de persistência e consulta no banco de dados.
 */
@Repository
public interface AdmRepository extends JpaRepository<Adm, Long> {
    // Ao estender JpaRepository, o Spring Data JPA gera automaticamente todas as
    // operações básicas de CRUD (Criar, Ler, Atualizar e Deletar) para a entidade 'Adm'.
    // O 'Long' indica o tipo de dado da chave primária (@Id) da entidade.
}