package com.example.application.apresentacao.gestor;

import com.example.application.entidade.FormCadastroP;
import com.example.application.entidade.PeriodoMatricula;
import com.example.application.persistencia.RegistrationServiceP;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.time.format.DateTimeFormatter;

@PermitAll
@Route(value = "cadastro_periodo", layout = LayoutGestor.class)
@PageTitle("Período Matricula Cadastradas  ")
public class CadastroPView extends VerticalLayout {

    private Grid<PeriodoMatricula> gridPeriodo = new Grid<>(PeriodoMatricula.class);
    private FormCadastroP formPeriodo = new FormCadastroP();
    private RegistrationServiceP servicePeriodo;
    private Button addButton;

    public CadastroPView(RegistrationServiceP servicePeriodo) {
        this.servicePeriodo = servicePeriodo;
        addClassName("list-apresentacao");
        setSizeFull();
        configureGridPeriodo();
        configureFormPeriodo();

        add(getTitle(), getContentPeriodo());
        updateListPeriodo();
        closeEditorPeridodo();
    }

    private HorizontalLayout getTitle() {
        H2 title = new H2("PERIODO DE MATRICULA");
        addButton = new Button("Adicionar");
        addButton.setEnabled(false);
        addButton.addClickListener(e -> addNewPeriodo());

        HorizontalLayout titleLayout = new HorizontalLayout(title, addButton);
        titleLayout.setAlignItems(Alignment.CENTER);
        return titleLayout;
    }

    private HorizontalLayout getContentPeriodo() {
        HorizontalLayout content = new HorizontalLayout(gridPeriodo, formPeriodo);
        content.setFlexGrow(2, gridPeriodo);
        content.setFlexGrow(1, formPeriodo);
        content.addClassNames("content");
        content.setWidthFull();
        gridPeriodo.setHeight("100px"); // Define a altura da grade para mostrar apenas uma linha
        return content;
    }

    private void configureFormPeriodo() {
        formPeriodo.setWidth("25em");
        formPeriodo.addSaveListener(this::saveContactPeriodo);
        formPeriodo.addCloseListener(e -> closeEditorPeridodo());
    }

    private void configureGridPeriodo() {
        gridPeriodo.addClassNames("contact-grid");
        gridPeriodo.setColumns("id");

        gridPeriodo.getColumnByKey("id").setHeader("ID");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        gridPeriodo.addColumn(periodo -> periodo.getDataInicio() != null ? periodo.getDataInicio().format(formatter) : "")
                .setHeader("Data Início")
                .setKey("dataInicio");

        gridPeriodo.addColumn(periodo -> periodo.getDataFim() != null ? periodo.getDataFim().format(formatter) : "")
                .setHeader("Data Fim")
                .setKey("dataFim");

        gridPeriodo.getColumns().forEach(col -> col.setAutoWidth(true));

        gridPeriodo.asSingleSelect().addValueChangeListener(event ->
                editContactPeriodo(event.getValue()));
    }

    public void editContactPeriodo(PeriodoMatricula periodo) {
        if (periodo == null) {
            closeEditorPeridodo();
        } else {
            formPeriodo.setContact(periodo);
            formPeriodo.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditorPeridodo() {
        formPeriodo.setContact(null);
        formPeriodo.setVisible(false);
        removeClassName("editing");
    }

    private void updateListPeriodo() {
        gridPeriodo.setItems(servicePeriodo.getPeriodo());
        addButton.setEnabled(servicePeriodo.getPeriodo().isEmpty());
    }

    private void saveContactPeriodo(FormCadastroP.SaveEvent event) {
        servicePeriodo.register(event.getContact());
        updateListPeriodo();
        closeEditorPeridodo();
    }

    private void addNewPeriodo() {
        gridPeriodo.asSingleSelect().clear();
        editContactPeriodo(new PeriodoMatricula());
    }
}
