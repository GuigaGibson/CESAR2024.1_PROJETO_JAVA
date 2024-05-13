package com.example.application.entity;

import jakarta.persistence.Entity;

@Entity
public class Turma extends AbstractEntity {
    private String name;

    public Turma() {

    }

    public Turma(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
