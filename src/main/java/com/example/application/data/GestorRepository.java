package com.example.application.data;

import com.example.application.entity.Aluno;
import com.example.application.entity.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface GestorRepository extends JpaRepository<Gestor, Long> {

        @Query("SELECT g FROM Gestor g WHERE g.nome LIKE %:nome%")
        List<Gestor> search(@Param("nome") String nome);
    }

    
