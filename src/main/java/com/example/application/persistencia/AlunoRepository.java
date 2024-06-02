package com.example.application.persistencia;

import com.example.application.entidade.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("SELECT a FROM Aluno a WHERE a.nome LIKE %:nome%")
    List<Aluno> search(@Param("nome") String nome);

    @Query("SELECT a FROM Aluno a WHERE (a.email = :parametro OR a.matricula = :parametro) AND a.senha = :senha")
    Aluno validarLogin(@Param("parametro") String parametro, @Param("senha") String senha);

}

