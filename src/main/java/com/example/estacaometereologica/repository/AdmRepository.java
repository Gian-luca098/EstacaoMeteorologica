package com.example.estacaometereologica.repository;

import com.example.estacaometereologica.model.Adm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AdmRepository extends JpaRepository<Adm,Long> {

}
