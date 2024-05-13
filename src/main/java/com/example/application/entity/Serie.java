package com.example.application.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.Formula;

import java.util.LinkedList;
import java.util.List;

@Entity
public class Serie extends AbstractEntity {
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "serie")
    @Nullable
    private List<Aluno> employees = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Aluno> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Aluno> employees) {
        this.employees = employees;
    }

    @Formula("(select count(c.id) from Aluno c where c.serie_id = id)")
    private int employeeCount;

    public int getEmployeeCount(){
        return employeeCount;
    }
}
