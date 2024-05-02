package com.example.softwareContabilidade.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal precoCompra;

    private BigDecimal precoVenda;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    @JsonBackReference
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Compra> compra;
}
