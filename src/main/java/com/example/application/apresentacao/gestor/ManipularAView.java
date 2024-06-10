package com.example.application.apresentacao.gestor;

import com.example.application.entidade.Aluno;
import com.example.application.entidade.FormCadastroA;
import com.example.application.persistencia.RegistrationServiceA;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.stream.Collectors;

@PermitAll
@Route(value = "aluno/home", layout = LayoutGestor.class)
@PageTitle("Manipular Usuários | Trilhas Eletivas")
public class ManipularAView extends VerticalLayout {

    private Grid<Aluno> grid = new Grid<>(Aluno.class);
    private TextField filterText = new TextField();
    private FormCadastroA form = new FormCadastroA();
    private RegistrationServiceA service;

    public ManipularAView(RegistrationServiceA service) {
        this.service = service;
        addClassName("list-apresentacao");
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
        grid.setColumns("matricula", "nome", "email", "senha", "serie", "turma", "eletiva");
        grid.getColumnByKey("matricula").setHeader("Matrícula");
        grid.getColumnByKey("nome").setHeader("Nome");
        grid.getColumnByKey("email").setHeader("Email");
        grid.getColumnByKey("senha").setHeader("Senha");
        grid.getColumnByKey("serie").setHeader("Série");
        grid.getColumnByKey("turma").setHeader("Turma");
        grid.getColumnByKey("eletiva").setHeader("Eletiva");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editContact(event.getValue()));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Pesquisar");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        var toolbar = new HorizontalLayout(filterText);
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

    private void updateList() {
        String filter = filterText.getValue().toLowerCase().trim();
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
