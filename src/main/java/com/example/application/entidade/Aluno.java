package com.example.application.entidade;

import com.vaadin.flow.component.textfield.TextField;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;


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


    private String eletiva = " ";

    @NotEmpty
    private String senha = " ";

    //@NotEmpty
    // private boolean primeiroLogin = true;


    public Aluno() {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.serie = serie;
        this.turma = turma;
        this.eletiva = eletiva;
        this.senha = senha;
        // this.primeiroLogin = primeiroLogin;
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

    public void setNome(String nome) {
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
    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }


    ///
    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }


    ///
    public String getEletiva() {
        return eletiva;
    }

    public void setEletiva(String eletiva) {
        this.eletiva = eletiva;
    }


    ///
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isPrimeiroLogin() {
        return true;

    }


    //public void setPrimeiroLogin(boolean primeiroLogin) {
    //    this.primeiroLogin = primeiroLogin;
    //}

}
