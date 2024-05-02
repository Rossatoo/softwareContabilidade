package com.example.softwareContabilidade.repository;

import com.example.softwareContabilidade.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
