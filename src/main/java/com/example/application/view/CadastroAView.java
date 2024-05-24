package com.example.application.view;

import com.example.application.data.services.RegistrationServiceA;
import com.example.application.entity.Aluno;
import com.example.application.entity.FormCadastroA;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;



@Route(value = "cadastro_aluno", layout = LayoutPrincipalAluno.class)
@PageTitle("Alunos cadastrados | Trilhas EletivasView ")

public class CadastroAView extends VerticalLayout {

    private Grid<Aluno> grid = new Grid<>(Aluno.class);
    private TextField filterText = new TextField();
    private FormCadastroA form = new FormCadastroA();
    private RegistrationServiceA service;

    public CadastroAView(RegistrationServiceA service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();

    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form.setWidth("25em");
        form.addSaveListener(this::saveContact);
        form.addDeleteListener(this::deleteContact);
        form.addCloseListener(e -> closeEditor());
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("matricula", "nome","email", "senha", "serie", "turma");
        grid.getColumnByKey("matricula").setHeader("Matrícula");
        grid.getColumnByKey("nome").setHeader("Nome");
        grid.getColumnByKey("email").setHeader("Email");
        grid.getColumnByKey("senha").setHeader("Senha");
        grid.getColumnByKey("serie").setHeader("Serie");
        grid.getColumnByKey("turma").setHeader("Turma");



        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editContact(event.getValue()));


    }

    private Component getToolbar() {
        filterText.setPlaceholder("Pesquisar");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Adicionar aluno");
        addContactButton.addClassName("black-link");
        addContactButton.addClickListener(click -> addContact());

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editContact(Aluno aluno) {
        if (aluno == null) {
            closeEditor();
        } else {
            form.setContact(aluno);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addContact() {
        grid.asSingleSelect().clear();
        editContact(new Aluno());
    }

    private void updateList() {
        String filter = filterText.getValue().toLowerCase().trim(); // Obtém o valor do filtro e o normaliza

        // Define os alunos filtrados diretamente no Grid, aplicando o filtro ao obter a lista do serviço
        grid.setItems(service.getAllAlunos().stream()
                .filter(aluno -> aluno.getNome().toLowerCase().contains(filter))
                .collect(Collectors.toList()));
    }


    private void saveContact(FormCadastroA.SaveEvent event) {
        service.register(event.getContact());
        updateList();
        closeEditor();
    }

    private void deleteContact(FormCadastroA.DeleteEvent event) {
        service.delete(event.getContact());
        updateList();
        closeEditor();
    }
}
