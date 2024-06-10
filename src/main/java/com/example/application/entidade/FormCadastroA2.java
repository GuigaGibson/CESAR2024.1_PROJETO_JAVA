package com.example.application.entidade.forms;

import com.example.application.entidade.Aluno;
import com.example.application.persistencia.RegistrationServiceA;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.shared.Registration;
import jakarta.annotation.security.PermitAll;


@PermitAll
public class FormCadastroA2 extends FormLayout {

    BeanValidationBinder<Aluno> binder = new BeanValidationBinder<>(Aluno.class);

    public void setContact(Aluno aluno) {
        binder.setBean(aluno);
    }

    TextField matricula = new TextField("Matrícula");
    TextField nome = new TextField("Nome");
    EmailField email = new EmailField("Email");
    PasswordField senha = new PasswordField("Senha");
    ComboBox<String>  serie = new ComboBox<>("Serie");
    ComboBox<String> turma = new ComboBox<>("Turma");




    Button save = new Button("Salvar");
    Button close = new Button("Cancelar");


    public FormCadastroA2() {
        addClassName("aluno-form");
        binder.bindInstanceFields(this);

        configureCombox();
        add(matricula,
                nome,
                email,
                senha,
                serie,
                turma,
                createButtonsLayout());
    }

    private void configureCombox() {
        RegistrationServiceA service = new RegistrationServiceA();
        serie.setItems(service.getSerie());
        turma.setItems(service.getTurma());
    }


    private Component createButtonsLayout() { // botoes do formulario
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY); //tema
        close.addClassName("green-link");

        save.addClickShortcut(Key.ENTER); //atalho
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave()); //metoodo para salvar
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid())); //ativa/desativa a aptidao do botao
        return new HorizontalLayout(save, close);

    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }


    // Events
    public static abstract class ContactFormEvent extends ComponentEvent<FormCadastroA2> {
        private final Aluno aluno;

        protected ContactFormEvent(FormCadastroA2 source, Aluno aluno) {
            super(source, false);
            this.aluno = aluno;
        }

        public Aluno getContact() {
            return aluno;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(FormCadastroA2 source, Aluno aluno) {
            super(source, aluno);
        }
    }


    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(FormCadastroA2 source) {
            super(source, null);
        }
    }


    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}