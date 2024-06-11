package com.example.softwareContabilidade.repository;

import com.example.softwareContabilidade.model.Empresa;
import com.example.softwareContabilidade.model.IcmsReceber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}
