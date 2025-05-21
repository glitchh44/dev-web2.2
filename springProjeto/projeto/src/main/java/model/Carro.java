// src/main/java/com/example/projeto/model/Carro.java
package com.example.projeto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.math.BigDecimal;

@Entity
@Table(name = "carros") // nome da tabela no banco
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private String marca;
    private Integer ano;
    private BigDecimal valor;

    // Construtor vazio obrigatório para JPA
    public Carro() {}

    // Construtor com argumentos
    public Carro(String modelo, String marca, Integer ano, BigDecimal valor) {
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.valor = valor;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAno() {
        return ano;
    }
    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
