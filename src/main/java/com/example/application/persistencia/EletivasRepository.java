package com.example.application.persistencia;

import com.example.application.entidade.Aluno;
import com.example.application.entidade.Eletivas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EletivasRepository extends JpaRepository <Eletivas , Long>{

    @Query("SELECT a FROM Eletivas a WHERE a.nome LIKE %:nome%")
    List<Eletivas> search(@Param("nome") String nome);

    @Query("SELECT e.alunos FROM Eletivas e WHERE e.id = :eletivaId")
    List<Aluno> findAlunosByEletivaId(@Param("eletivaId") Long eletivaId);

}
