package com.elcio.backlog.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "plataformas")
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "NOME",nullable = false, length = 100)
    private String nome;

    public Plataforma() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
