package com.example.application.persistencia;

import com.example.application.entidade.Eletivas;
import com.example.application.entidade.PeriodoMatricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class RegistrationServiceP {
    @Autowired
    private PeriodoMatriculaRepository periodoMatriculaRepository;

    public void register(PeriodoMatricula periodo) {
        periodoMatriculaRepository.save(periodo);
    }

    public List<PeriodoMatricula> getPeriodo() {
        return periodoMatriculaRepository.findAll();
    }

    public boolean isPeriodoMatriculaAtivo() {
        PeriodoMatricula periodo = periodoMatriculaRepository.findTopByOrderByDataInicioDesc();
        LocalDate hoje = LocalDate.now();
        return periodo != null && !hoje.isBefore(periodo.getDataInicio()) && !hoje.isAfter(periodo.getDataFim());
    }
}
