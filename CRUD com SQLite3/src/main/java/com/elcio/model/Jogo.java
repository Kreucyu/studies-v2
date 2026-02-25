package com.elcio.model;

import java.time.LocalDate;

public class Jogo {
    public String nome;
    public LocalDate dataDeLancamento;
    public String plataforma;
    public int id;
    public String genero;

    public Jogo(String nome, LocalDate dataDeLancamento, String plataforma, int id, String genero) {
        this.nome = nome;
        this.dataDeLancamento = dataDeLancamento;
        this.genero = genero;
        this.id = id;
        this.plataforma = plataforma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(LocalDate dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Gênero: " + genero + ", Plataforma: " + plataforma +", Data de lançamento: " + dataDeLancamento;
    }
}
