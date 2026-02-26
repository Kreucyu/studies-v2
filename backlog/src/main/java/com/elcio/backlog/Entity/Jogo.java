package com.elcio.backlog.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jogos")
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "NOME",nullable = false, length = 100)
    private String nome;

    @Column(name = "DATA",nullable = false)
    private LocalDate dataLancamento;

    @ManyToMany
    @JoinTable(
            name = "jogo_plataforma",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "plataforma_id"))
    private Set<Plataforma> plataformas = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "jogo_genero",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id"))
    private Set<Genero> generos = new HashSet<>();

    public Jogo() {
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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Set<Plataforma> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(Set<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }

    public Set<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(Set<Genero> generos) {
        this.generos = generos;
    }
}
