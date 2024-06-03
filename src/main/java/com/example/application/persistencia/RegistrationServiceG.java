package com.example.application.persistencia;

import com.example.application.entidade.Gestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceG {
    @Autowired
    private GestorRepository repositoryG;

    public void register(Gestor gestor) {
        repositoryG.save(gestor);
    }
}
