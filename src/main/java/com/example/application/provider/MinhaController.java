package com.example.application.provider;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MinhaController {

    @PreAuthorize("hasRole('ROLE_ALUNO')")
    @GetMapping("/minha-rota-aluno")
    public String minhaRotaAluno() {
        // Lógica para a rota acessível apenas para alunos
        return "minhaViewAluno";
    }

    @PreAuthorize("hasRole('ROLE_GESTOR')")
    @GetMapping("/minha-rota-gestor")

    public String minhaRotaGestor() {
        // Lógica para a rota acessível apenas para gestores
        return "minhaViewGestor";
    }
}
