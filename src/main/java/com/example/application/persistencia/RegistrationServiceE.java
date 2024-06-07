package com.example.application.persistencia;

import com.example.application.entidade.Aluno;
import com.example.application.entidade.Eletivas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RegistrationServiceE {

    @Autowired
    EletivasRepository repositoryE;

    @Autowired
    private AlunoRepository alunoRepository;

    public void register(Eletivas eletiva) {
        repositoryE.save(eletiva);
    }

    public void delete(Eletivas eletiva) {
        repositoryE.delete(eletiva);
    }

    public List<Eletivas> getAllEletivas() {
        return repositoryE.findAll();
    }

    //public List<Eletivas> getEletivasByAlunoId(Long alunoId) {
       // return alunoRepository.findEletivasByAlunoId(alunoId);
   // }

    public List<Aluno> getAlunosByEletivaId(Long eletivaId) {
        return repositoryE.findAlunosByEletivaId(eletivaId);
    }
}

