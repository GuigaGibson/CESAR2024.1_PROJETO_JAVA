package com.example.application.entidade;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class PeriodoMatricula extends AbstractEntity {

    private LocalDate dataInicio;
    private LocalDate dataFim;

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
