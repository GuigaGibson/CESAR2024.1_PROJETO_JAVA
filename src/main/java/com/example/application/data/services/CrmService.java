package com.example.application.data.services;

import com.example.application.entity.Aluno;
import com.example.application.entity.Serie;
import com.example.application.entity.Turma;
import com.example.application.data.TurmaRepository;
import com.example.application.data.AlunoRepository;
import com.example.application.data.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {

    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final SerieRepository serieRepository;

    public CrmService(AlunoRepository alunoRepository,
                      TurmaRepository turmaRepository,
                      SerieRepository serieRepository) {
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
        this.serieRepository = serieRepository;
    }

    public List<Aluno> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return alunoRepository.findAll();
        } else {
            return alunoRepository.search(stringFilter);
        }
    }

    public long countContacts() {
        return alunoRepository.count();
    }

    public void deleteContact(Aluno aluno) {
        alunoRepository.delete(aluno);
    }

    public void saveContact(Aluno aluno) {
        if (aluno == null) {
            System.err.println("Aluno is null. Are you sure you have connected your form to the application?");
            return;
        }
        alunoRepository.save(aluno);
    }

    public List<Serie> findAllCompanies() {
        return turmaRepository.findAll();
    }

    public List<Turma> findAllStatuses(){
        return serieRepository.findAll();
    }
}