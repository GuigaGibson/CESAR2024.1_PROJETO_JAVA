package com.example.application.apresentacao.gestor;

import com.example.application.entidade.Eletivas;
import com.example.application.entidade.FormCadastroE;
import com.example.application.entidade.PeriodoMatricula;
import com.example.application.persistencia.RegistrationServiceE;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.stream.Collectors;

@PermitAll
@Route(value = "cadastro_eletiva", layout = LayoutGestor.class)
@PageTitle("Eletivas Cadastradas | Trilhas EletivasView ")
public class CadastroEView extends VerticalLayout {
    private Grid<Eletivas> grid = new Grid<>(Eletivas.class);
    private TextField filterText = new TextField();
    private FormCadastroE form = new FormCadastroE();
    private RegistrationServiceE service;

    public CadastroEView(RegistrationServiceE service) {
        this.service = service;
        addClassName("list-apresentacao");
        setSizeFull();
        configureGrid();
        configureForm();
        add(getTitle(), getToolbar(), getContent());
        updateList();
        closeEditor();

    }

    private H2 getTitle() {
        return new H2("Cadastro de eletivas");

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
        grid.setColumns("nome", "descricao", "professor", "serie"); // Adiciona a coluna "serie"
        grid.getColumnByKey("nome").setHeader("Nome");
        grid.getColumnByKey("descricao").setHeader("Descrição");
        grid.getColumnByKey("professor").setHeader("Professor");

        grid.getColumnByKey("serie").setHeader("Série"); // Define o cabeçalho da coluna "serie"


        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editContact(event.getValue()));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Pesquisar");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Adicionar eletiva");
        addContactButton.addClassName("black-link");
        addContactButton.addClickListener(click -> addContact());

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editContact(Eletivas eletiva) {
        if (eletiva == null) {
            closeEditor();
        } else {
            form.setContact(eletiva);
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
        editContact(new Eletivas());
    }

    private void updateList() {
        String filter = filterText.getValue().toLowerCase().trim(); // Obtém o valor do filtro e o normaliza

        // Define os alunos filtrados diretamente no Grid, aplicando o filtro ao obter a lista do serviço
        grid.setItems(service.getAllEletivas().stream()
                .filter(eletiva -> eletiva.getNome().toLowerCase().contains(filter))
                .collect(Collectors.toList()));
    }

    private void saveContact(FormCadastroE.SaveEvent event) {
        service.register(event.getContact());
        updateList();
        closeEditor();
    }

    private void deleteContact(FormCadastroE.DeleteEvent event) {
        service.delete(event.getContact());
        updateList();
        closeEditor();
    }

}
