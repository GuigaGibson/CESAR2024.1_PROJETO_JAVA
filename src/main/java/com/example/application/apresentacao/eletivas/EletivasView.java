package com.example.application.apresentacao.eletivas;

import com.example.application.apresentacao.aluno.LayoutAluno;
import com.example.application.entidade.Eletivas;
import com.example.application.entidade.Aluno;
import com.example.application.persistencia.AlunoRepository;
import com.example.application.persistencia.RegistrationServiceA;
import com.example.application.persistencia.RegistrationServiceE;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@PermitAll
@Route(value = "eletivas", layout = LayoutAluno.class)
@PageTitle("Eletivas Cadastradas | Trilhas EletivasView ")
public class EletivasView extends VerticalLayout {
    private VerticalLayout eletivasLayout = new VerticalLayout();
    private RegistrationServiceE service;

    @Autowired
    private RegistrationServiceA alunoService;

    @Autowired
    private AlunoRepository alunoRepository;

    private Authentication alunoAutenticado = SecurityContextHolder.getContext().getAuthentication();

    public EletivasView(RegistrationServiceE service) {
        this.service = service;
        setSizeFull();
        add(eletivasLayout);
        updateList();
    }

    private void updateList() {
        eletivasLayout.removeAll();
        service.getAllEletivas().forEach(this::addEletivaToLayout);
    }

    private void addEletivaToLayout(Eletivas eletiva) {
        Button detailsButton = new Button("Detalhes");
        detailsButton.addClassName("padding");
        detailsButton.addClickListener(e -> showDetails(eletiva));

        Div eletivaDiv = new Div();
        eletivaDiv.addClassName("eletiva");
        eletivaDiv.setText("Nome: " + eletiva.getNome());

        Div whiteBox = new Div();
        whiteBox.addClassName("white-box");
        whiteBox.add(eletivaDiv, detailsButton);

        eletivasLayout.add(whiteBox);
    }

    private void showDetails(Eletivas eletiva) {
        Dialog dialog = new Dialog();
        dialog.setWidth("400px");
        dialog.setHeight("300px");

        VerticalLayout layout = new VerticalLayout();
        layout.add(new Div("Nome: " + eletiva.getNome()));
        layout.add(new Div("Descrição: " + eletiva.getDescricao()));
        layout.add(new Div("Professor: " + eletiva.getProfessor()));
        layout.add(new Div("Série: " + eletiva.getSerie())); // Adicionando a série da disciplina

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Aluno aluno = alunoRepository.findByEmail(email);

        if (aluno.getSerie() == eletiva.getSerie()) {
            Button cadastrarButton = new Button("Inscrever-se");
            cadastrarButton.addClassName("green-link");
            cadastrarButton.addClassName("black-link");
            cadastrarButton.addClickListener(event -> {
                try {
                    alunoService.matricularAlunoEmEletiva(aluno.getId(), eletiva.getId());
                    sucessoAoMatricular();
                } catch (Exception e) {
                    Div mensagemErro = new Div();
                    mensagemErro.setText("Erro: " + e.getMessage());
                    mensagemErro.addClassName("mensagem-erro"); // Adicionando a classe CSS
                    layout.add(mensagemErro);
                    dialog.setWidth("500px"); // Ajustando a largura do diálogo para acomodar a mensagem de erro
                }
                dialog.close();
            });
            layout.add(cadastrarButton);
        } else {
            Div mensagemErro = new Div();
            mensagemErro.setText("Você não está autorizado a se inscrever nesta disciplina.");
            mensagemErro.addClassName("mensagem-erro"); // Adicionando a classe CSS
            layout.add(mensagemErro);
            dialog.setWidth("500px"); // Ajustando a largura do diálogo para acomodar a mensagem de erro
        }

        dialog.add(layout);
        dialog.open();
    }

    private void sucessoAoMatricular() {
        Dialog dialog = new Dialog();
        dialog.setWidth("400px");
        dialog.setHeight("300px");

        VerticalLayout layout = new VerticalLayout();
        layout.add(new Div("Sucesso ao matricular"));
        Button fecharButton = new Button("Fechar");
        fecharButton.addClassName("green-link");
        fecharButton.addClassName("black-link");
        fecharButton.addClickListener(event -> dialog.close());

        layout.add(fecharButton);
        dialog.add(layout);
        dialog.open();
    }

    private void erroAoMatricular(String erro) {
        Dialog dialog = new Dialog();
        dialog.setWidth("400px");
        dialog.setHeight("300px");

        VerticalLayout layout = new VerticalLayout();
        layout.add(new Div(erro));
        Button fecharButton = new Button("Fechar");
        fecharButton.addClassName("green-link");
        fecharButton.addClassName("black-link");
        fecharButton.addClickListener(event -> dialog.close());

        layout.add(fecharButton);
        dialog.add(layout);
        dialog.open();
    }
}
