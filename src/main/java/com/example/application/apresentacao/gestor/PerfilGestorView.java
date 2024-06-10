package com.example.application.apresentacao.gestor;

import com.example.application.apresentacao.gestor.LayoutGestor;
import com.example.application.entidade.Gestor;
import com.example.application.persistencia.GestorRepository;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@PermitAll
@Route(value = "perfilG", layout = LayoutGestor.class)
public class PerfilGestorView extends VerticalLayout {

    private final GestorRepository gestorRepository;

    @Autowired
    public PerfilGestorView(GestorRepository gestorRepository) {
        this.gestorRepository = gestorRepository;

        // Obtendo as informações do usuário autenticado no momento
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName(); // Nome de usuário

        // Obtendo as informações do gestor do banco de dados
        Gestor gestor = gestorRepository.findByEmail(nomeUsuario);

        // Componentes para exibir as informações do perfil
        TextField nomeField = new TextField("Nome");
        nomeField.setValue(gestor.getNome());
        nomeField.setReadOnly(true);

        TextField matriculaField = new TextField("Matrícula");
        matriculaField.setValue(gestor.getMatricula());
        matriculaField.setReadOnly(true);

        TextField emailField = new TextField("E-mail");
        emailField.setValue(gestor.getEmail());
        emailField.setReadOnly(true);

        TextField senhaField= new TextField("Senha");
        senhaField.setValue(gestor.getSenha());
        senhaField.setReadOnly(true);

        // Componente para a foto do gestor
        Image fotoGestor = new Image("images/perfil.png", "Foto do Gestor");
        fotoGestor.setWidth("200px");
        fotoGestor.setHeight("200px");

        // Layout para organizar os componentes
        VerticalLayout innerLayout = new VerticalLayout(fotoGestor, nomeField, matriculaField, emailField, senhaField);
        innerLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        innerLayout.setSpacing(true);
        innerLayout.setPadding(true);
        innerLayout.setSizeFull();

        // Adiciona a classe CSS para a caixa branca
        innerLayout.addClassName("profile-box");

        // Adiciona o layout do perfil dentro de uma caixa branca
        add(innerLayout);
    }
}
