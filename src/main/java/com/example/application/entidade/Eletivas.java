package com.example.application.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Eletivas  extends AbstractEntity{


    @NotEmpty
    private String nome = "";

    @NotEmpty
    private String descricao = "";

    @NotEmpty
    private String professor = "";




    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor= professor;
    }



    @Override
    public String toString() {
        return "Eletivas{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", professor='" + professor + '\'' +
                '}';
    }



}
