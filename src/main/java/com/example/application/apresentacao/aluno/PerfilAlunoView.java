package com.example.application.apresentacao.aluno;

import com.example.application.apresentacao.aluno.LayoutAluno;
import com.example.application.entidade.Aluno;
import com.example.application.persistencia.AlunoRepository;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@PermitAll
@Route(value = "perfilA", layout = LayoutAluno.class)

public class PerfilAlunoView extends VerticalLayout {

    private final AlunoRepository alunoRepository;

    @Autowired
    public PerfilAlunoView(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;

        // Obtendo as informações do usuário autenticado no momento
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName(); // Nome de usuário

        // Obtendo as informações do aluno do banco de dados
        Aluno aluno = alunoRepository.findByEmail(nomeUsuario);

        // Componentes para exibir as informações do perfil
        TextField nomeField = new TextField("Nome");
        nomeField.setValue(aluno.getNome());
        nomeField.setReadOnly(true);

        TextField matriculaField = new TextField("Matrícula");
        matriculaField.setValue(aluno.getMatricula());
        matriculaField.setReadOnly(true);

        TextField emailField = new TextField("E-mail");
        emailField.setValue(aluno.getEmail());
        emailField.setReadOnly(true);

        TextField senhaField= new TextField("Senha");
        senhaField.setValue(aluno.getSenha());
        senhaField.setReadOnly(true);

        // Componente para a foto do aluno
        Image fotoAluno = new Image("images/perfil.png", "Foto do Aluno");
        fotoAluno.setWidth("200px");
        fotoAluno.setHeight("200px");

        // Layout para organizar os componentes
        VerticalLayout innerLayout = new VerticalLayout(fotoAluno, nomeField, matriculaField, emailField, senhaField);
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
