package com.example.softwareContabilidade.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "fornecedores")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpfCnpj;

    private String endereco;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Produto> produto;
}
