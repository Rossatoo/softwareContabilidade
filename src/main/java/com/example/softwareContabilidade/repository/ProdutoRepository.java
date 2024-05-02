package com.example.softwareContabilidade.repository;

import com.example.softwareContabilidade.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
