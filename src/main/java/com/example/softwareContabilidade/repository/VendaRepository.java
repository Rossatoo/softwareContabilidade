package com.example.softwareContabilidade.repository;

import com.example.softwareContabilidade.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
