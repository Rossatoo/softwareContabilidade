package com.example.softwareContabilidade.repository;

import com.example.softwareContabilidade.model.Patrimonio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Long> {
    // Métodos CRUD são fornecidos pelo JpaRepository
}