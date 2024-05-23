package com.example.application.data.services;

import com.example.application.data.AlunoRepository;
import com.example.application.entity.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RegistrationServiceA {

    @Autowired
    private AlunoRepository repositoryA;

    public List<String> getSerie() {
        return Arrays.asList("1 ANO", "2 ANO", "3 ANO"); // setar os valores do combox serie
    }

    public List<String> getTurma() {
        return Arrays.asList("A", "B", "C"); // setar os valores do combox turma
    }

    // metodos para editar a lista de alunos do formulário

    public void register(Aluno aluno) {
        repositoryA.save(aluno);
    }

    public void delete(Aluno aluno) {
        repositoryA.delete(aluno);
    }

    public List<Aluno> getAllAlunos() {
        return repositoryA.findAll();
    }
}