package com.elcio.backlog.Entity;

import jakarta.persistence.*;

@Entity
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID")
    private Long id;

    @Column(name= "NOME",nullable = false, length = 100)
    private String nome;

    private Set<Plataforma> plataformas;

}
