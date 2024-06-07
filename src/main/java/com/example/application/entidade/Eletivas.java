package com.example.application.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Eletivas  extends AbstractEntity{


    @NotEmpty
    private String nome = "";

    @NotEmpty
    private String descricao = "";

    @NotEmpty
    private String professor = "";

    private int vagasDisponiveis;

    @OneToMany(mappedBy = "eletiva")
    private List<Aluno> alunos = new ArrayList<>();

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

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(int vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return nome;
        //return "Eletivas{" +
                //"nome='" + nome + '\''+
                //", descricao='" + descricao + '\'' +
                //", professor='" + professor + '\'' +
                //'}';
    }



}