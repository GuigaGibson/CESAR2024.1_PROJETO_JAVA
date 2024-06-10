package com.example.application.entidade;

import com.vaadin.flow.component.textfield.TextField;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Aluno extends AbstractEntity {


    @NotEmpty
    private String matricula = "";

    @NotEmpty
    private String nome = "";

    @NotEmpty
    private String email = " ";

    @NotEmpty
    private String serie;

    @NotEmpty
    private String turma;

    @NotEmpty
    private String senha = " ";

    @ManyToOne
    @JoinColumn(name = "eletiva_id")
    private Eletivas eletiva;

    public Aluno() {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.serie = serie;
        this.turma = turma;
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Eletivas getEletiva() {
        return eletiva;
    }

    public void setEletiva(Eletivas eletiva) {
        this.eletiva = eletiva;
    }
}
