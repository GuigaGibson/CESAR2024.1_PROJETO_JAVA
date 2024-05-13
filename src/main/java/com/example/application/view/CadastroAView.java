package com.example.application.view;

import com.example.application.entity.Aluno;
import com.example.application.entity.Form;
import com.example.application.data.services.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


@PermitAll
@Route(value = "cadastro_aluno", layout = LayoutPrincipal.class)
@PageTitle("Alunos cadastrados | Trilhas EletivasView ")

public class CadastroAView extends VerticalLayout {
    Grid<Aluno> grid = new Grid<>(Aluno.class);
    TextField filterText = new TextField();
    Form form;
    CrmService service;

    public CadastroAView(CrmService service) {
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
        form = new Form(service.findAllCompanies(), service.findAllStatuses());
        form.setWidth("25em");
        form.addSaveListener(this::saveContact);
        form.addDeleteListener(this::deleteContact);
        form.addCloseListener(e -> closeEditor());
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("matricula", "nome", "eletiva");
        grid.getColumnByKey("matricula").setHeader("Matrícula");
        grid.getColumnByKey("nome").setHeader("Nome");
        grid.getColumnByKey("eletiva").setHeader("Eletiva");
        grid.addColumn(contact -> contact.getTurma().getName()).setHeader("Ano");
        grid.addColumn(contact -> contact.getSerie().getName()).setHeader("Turma");
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
        grid.setItems(service.findAllContacts(filterText.getValue()));
    }
    private void saveContact(Form.SaveEvent event) {
        service.saveContact(event.getContact());
        updateList();
        closeEditor();
    }

    private void deleteContact(Form.DeleteEvent event) {
        service.deleteContact(event.getContact());
        updateList();
        closeEditor();
    }
}