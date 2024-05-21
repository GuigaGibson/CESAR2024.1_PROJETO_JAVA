package com.example.application.data.services;

import com.example.application.data.GestorRepository;
import com.example.application.entity.Gestor;
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
