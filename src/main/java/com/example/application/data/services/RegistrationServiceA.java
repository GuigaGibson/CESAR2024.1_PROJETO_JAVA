package com.example.application.data.services;

import com.example.application.data.AlunoRepository;
import com.example.application.entity.Aluno;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A service class for the UI to access backend services.
 */
@Service
public class RegistrationServiceA {
    
    @Autowired
    private AlunoRepository repositoryA;

    public List<String> getSerie() {
        return Arrays.asList("1 ANO", "2 ANO", "3 ANO");
    }
    public List<String> getTurma() {
        return Arrays.asList("A", "B", "C"); }

    public void register(Aluno aluno) {
        repositoryA.save(aluno);
    }

}