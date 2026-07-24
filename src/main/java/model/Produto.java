package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "estoque",  nullable = false)
    private Integer estoque;

    @Column(name = "sku",  nullable = false)
    private String sku;

    public Produto() {}

    public Produto(String nome, Double preco, Integer estoque, String sku) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.sku = sku;
    }
}
