package com.example.softwareContabilidade.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonBackReference
    private Produto produto;

    @OneToOne(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private icmsReceber icmsReceber;
}
