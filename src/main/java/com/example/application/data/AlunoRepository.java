package com.example.application.data;


import com.example.application.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

        @Query("select c from Aluno c " +
                "where lower(c.matricula) like lower(concat('%', :searchTerm, '%')) " +
                "or lower(c.nome) like lower(concat('%', :searchTerm, '%'))")
        List<Aluno> search(@Param("searchTerm") String searchTerm);
    }

