package com.example.application.entidade;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;


@Entity
public class Gestor extends AbstractEntity {

    @NotEmpty
    private String matricula  = "";

    @NotEmpty
    private String nome = "";

    @NotEmpty
    private String email =" ";

    @NotEmpty
    private String senha = " ";



    public Gestor() {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.senha = senha;

    }



    ///
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    ///
    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = nome;
    }


    ///
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    ///
    public String getSenha() {
        return senha;
    }

    public void setSenha (String senha) {
        this.senha = senha;
    }


}
