package com.example.application.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Aluno extends AbstractEntity {



    @NotEmpty
    private String matricula = "";

    @NotEmpty
    private String nome = "";

    @ManyToOne
    @JoinColumn(name = "serie_id")
    @NotNull
    @JsonIgnoreProperties({"employees"})
    private Serie serie;

    @NotNull
    @ManyToOne
    private Turma turma;


    @NotEmpty
    private String eletiva= "";




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

    public Serie getSerie() {

        return serie;
    }

    public void setSerie(Serie serie) {

        this.serie = serie;
    }

    public Turma getTurma() {

        return turma;
    }

    public void setTurma(Turma turma) {

        this.turma = turma;
    }

    public String getEletiva() {

        return eletiva;
    }

    public void setEletiva(String eletiva) {

        this.eletiva = eletiva;
    }
}
