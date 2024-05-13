package com.example.application.data;


import com.example.application.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Serie, Long> {

}
