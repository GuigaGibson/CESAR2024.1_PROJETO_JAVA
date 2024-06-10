package com.example.application.persistencia;

import com.example.application.entidade.Aluno;
import com.example.application.entidade.Eletivas;
import com.example.application.entidade.PeriodoMatricula;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class RegistrationServiceA {

    @Autowired
    private AlunoRepository repositoryA;

    @Autowired
    private PeriodoMatriculaRepository  periodoMatriculaRepository;

    @Autowired
    private EletivasRepository eletivasRepository;


    public boolean verificarPeriodoMatricula() {
        LocalDate hoje = LocalDate.now();
        List<PeriodoMatricula> periodo = periodoMatriculaRepository.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(periodo.size() < 1){
            throw new RuntimeException("Ainda não temos um período para o início das matrículas");
        }
        if (hoje.isBefore(periodo.get(0).getDataInicio())) {
            throw new RuntimeException("As matriculas só se iniciam a partir de " + periodo.get(0).getDataInicio().format(formatter));
        } else if (hoje.isAfter(periodo.get(0).getDataFim())) {
            throw new RuntimeException("As matrículas foram até a data " + periodo.get(0).getDataInicio().format(formatter));
        }
        return true;
    }

    public List<String> getSerie() {
        return Arrays.asList("1° ANO", "2° ANO", "3° ANO"); // setar os valores do combox serie
    }

    public List<String> getTurma() {
        return Arrays.asList("A", "B", "C"); // setar os valores do combox turma
    }

    public void register(Aluno aluno) {
        repositoryA.save(aluno);
    }

    public void delete(Aluno aluno) {
        repositoryA.delete(aluno);
    }

    public List<Aluno> getAllAlunos() {
        return repositoryA.findAll();
    }

    @Transactional
    public void matricularAlunoEmEletiva(Long alunoId, Long eletivaId) {
        Aluno aluno = repositoryA.findById(alunoId).orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
        Eletivas novaEletiva = eletivasRepository.findById(eletivaId).orElseThrow(() -> new EntityNotFoundException("Eletiva não encontrada"));

        if (novaEletiva.getVagasDisponiveis() > 0) {
            Eletivas eletivaAtual = aluno.getEletiva();

            if (eletivaAtual != null && eletivaAtual.equals(novaEletiva)) {
                throw new IllegalStateException("O aluno já está matriculado nesta eletiva");
            }
            verificarPeriodoMatricula();

            if (eletivaAtual != null) {
                eletivaAtual.getAlunos().remove(aluno);
                eletivaAtual.setVagasDisponiveis(eletivaAtual.getVagasDisponiveis() + 1);
                eletivasRepository.save(eletivaAtual);
            }

            aluno.setEletiva(novaEletiva);
            novaEletiva.getAlunos().add(aluno);
            novaEletiva.setVagasDisponiveis(novaEletiva.getVagasDisponiveis() - 1);

            repositoryA.save(aluno);
            eletivasRepository.save(novaEletiva);
        } else {
            throw new IllegalStateException("Não há vagas disponíveis para esta eletiva");
        }
    }


}
