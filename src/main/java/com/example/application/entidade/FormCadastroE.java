package com.example.application.entidade;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.shared.Registration;
import jakarta.annotation.security.PermitAll;

@PermitAll
public class FormCadastroE extends FormLayout {

    private final BeanValidationBinder<Eletivas> binder = new BeanValidationBinder<>(Eletivas.class);

    private final TextField nome = new TextField("Nome");
    private final TextField descricao = new TextField("Descrição");
    private final TextField professor = new TextField("Professor");

    private final TextField quantidade = new TextField("Quantidade");

    private final TextField vagasDisponiveis = new TextField("Vagas Disponíveis");
    private final ComboBox<String> serie = new ComboBox<>("Série");


    private final Button save = new Button("Salvar");
    private final Button delete = new Button("Deletar");
    private final Button close = new Button("Cancelar");

    public FormCadastroE() {
        addClassName("eletiva-form");
        binder.bindInstanceFields(this);

        add(nome, descricao, professor,quantidade, createButtonsLayout());


        // Configure ComboBox
        serie.setItems("1° ANO", "2° ANO", "3° ANO");
        serie.setPlaceholder("Selecione a série");

        add(nome, descricao, professor, vagasDisponiveis, serie, createButtonsLayout());

    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClassName("green-link");

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    public void setContact(Eletivas eletiva) {
        binder.setBean(eletiva);
    }

    public static abstract class ContactFormEvent extends ComponentEvent<FormCadastroE> {
        private final Eletivas eletiva;

        protected ContactFormEvent(FormCadastroE source, Eletivas eletiva) {
            super(source, false);
            this.eletiva = eletiva;
        }

        public Eletivas getContact() {
            return eletiva;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(FormCadastroE source, Eletivas eletiva) {
            super(source, eletiva);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(FormCadastroE source, Eletivas eletiva) {
            super(source, eletiva);
        }
    }

    public static class CloseEvent extends ComponentEvent<FormCadastroE> {
        CloseEvent(FormCadastroE source) {
            super(source, false);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}
