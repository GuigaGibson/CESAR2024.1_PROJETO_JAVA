package com.example.application.data;


import com.example.application.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Turma, Long> {

}
