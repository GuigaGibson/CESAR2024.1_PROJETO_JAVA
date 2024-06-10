package com.example.application.persistencia;

import com.example.application.entidade.PeriodoMatricula;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PeriodoMatriculaRepository extends JpaRepository<PeriodoMatricula, Long> {
    PeriodoMatricula findTopByOrderByDataInicioDesc();

}
