package com.example.application.entidade;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import jakarta.annotation.security.PermitAll;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@PermitAll
public class FormCadastroP extends FormLayout {
    private final BeanValidationBinder<PeriodoMatricula> binder = new BeanValidationBinder<>(PeriodoMatricula.class);

    private final TextField dataInicio = new TextField("Início");
    private final TextField dataFim = new TextField("Fim");

    private final Button save = new Button("Salvar");
    private final Button delete = new Button("Deletar");
    private final Button close = new Button("Cancelar");

    public FormCadastroP() {
        addClassName("periodo-form");

        add(dataInicio, dataFim, createButtonsLayout());

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
    }

    private boolean isValidDate(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(value, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void showErrorDialog(String message) {
        Dialog dialog = new Dialog();
        dialog.add(message);
        Button closeButton = new Button("Fechar", event -> dialog.close());
        dialog.add(closeButton);
        dialog.open();
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClassName("green-link");

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new FormCadastroP.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new FormCadastroP.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        String inicioValue = dataInicio.getValue();
        String fimValue = dataFim.getValue();

        if (!isValidDate(inicioValue)) {
            showErrorDialog("Formato inválido para a data de início. Use dd/MM/yyyy.");
            return;
        }
        if (!isValidDate(fimValue)) {
            showErrorDialog("Formato inválido para a data de fim. Use dd/MM/yyyy.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inicioDate = LocalDate.parse(inicioValue, formatter);
        LocalDate fimDate = LocalDate.parse(fimValue, formatter);
        LocalDate currentDate = LocalDate.now();

        if (inicioDate.isAfter(fimDate)) {
            showErrorDialog("A data de início não pode ser posterior à data de fim.");
            return;
        }

        if (fimDate.isBefore(currentDate)) {
            showErrorDialog("A data de fim não pode ser anterior à data atual.");
            return;
        }

        try {
            PeriodoMatricula periodo = binder.getBean();
            periodo.setDataInicio(inicioDate);
            periodo.setDataFim(fimDate);
            fireEvent(new FormCadastroP.SaveEvent(this, periodo));
        } catch (Exception e) {
            showErrorDialog("Por favor, corrija os erros e tente novamente.");
        }
    }

    public void setContact(PeriodoMatricula periodo) {
        if (periodo != null) {
            if (periodo.getDataInicio() != null) {
                dataInicio.setValue(periodo.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            } else {
                dataInicio.clear();
            }
            if (periodo.getDataFim() != null) {
                dataFim.setValue(periodo.getDataFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            } else {
                dataFim.clear();
            }
        } else {
            dataInicio.clear();
            dataFim.clear();
        }
        binder.setBean(periodo);
    }

    public static abstract class ContactFormEvent extends ComponentEvent<FormCadastroP> {
        private final PeriodoMatricula periodo;

        protected ContactFormEvent(FormCadastroP source, PeriodoMatricula periodo) {
            super(source, false);
            this.periodo = periodo;
        }

        public PeriodoMatricula getContact() {
            return periodo;
        }
    }

    public static class SaveEvent extends FormCadastroP.ContactFormEvent {
        SaveEvent(FormCadastroP source, PeriodoMatricula periodo) {
            super(source, periodo);
        }
    }

    public static class DeleteEvent extends FormCadastroP.ContactFormEvent {
        DeleteEvent(FormCadastroP source, PeriodoMatricula periodo) {
            super(source, periodo);
        }
    }

    public static class CloseEvent extends ComponentEvent<FormCadastroP> {
        CloseEvent(FormCadastroP source) {
            super(source, false);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<FormCadastroP.DeleteEvent> listener) {
        return addListener(FormCadastroP.DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<FormCadastroP.SaveEvent> listener) {
        return addListener(FormCadastroP.SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<FormCadastroP.CloseEvent> listener) {
        return addListener(FormCadastroP.CloseEvent.class, listener);
    }
}
