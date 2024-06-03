package com.example.application.persistencia;

import com.example.application.entidade.Eletivas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RegistrationServiceE {

    @Autowired
    EletivasRepository repositoryE;


    // metodos para editar a lista de alunos do formulário

    public void register(Eletivas eletiva) {
        repositoryE.save(eletiva);
    }

    public void delete(Eletivas eletiva) {
        repositoryE.delete(eletiva);
    }

    public List<Eletivas> getAllEletivas() {
        return repositoryE.findAll();
    }
}

