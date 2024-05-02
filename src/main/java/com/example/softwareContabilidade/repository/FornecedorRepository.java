package com.example.softwareContabilidade.repository;

import com.example.softwareContabilidade.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
