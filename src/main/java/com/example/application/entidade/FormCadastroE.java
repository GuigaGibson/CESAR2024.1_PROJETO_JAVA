package com.example.application.entidade;

import com.example.application.persistencia.RegistrationServiceE;
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

        BeanValidationBinder<Eletivas> binder = new BeanValidationBinder<>(Eletivas.class);

        public void setContact(Eletivas eletiva) {
            binder.setBean(eletiva);
        }

        TextField nome = new TextField("Nome");
        TextField descricao = new TextField("Descrição");
        TextField professor= new TextField("Professor");


        Button save = new Button("Salvar");
        Button delete = new Button("Deletar");
        Button close = new Button("Cancelar");


        public FormCadastroE() {
            addClassName("eletiva-form");
            binder.bindInstanceFields(this);

            //configureCombox();
            add(nome,
                    descricao,
                    professor,
                    createButtonsLayout());
        }

        //private void configureCombox() {
           // RegistrationServiceE service = new RegistrationServiceE();
           // eletiva.setItems(service.getCargaHoraria());

        //}


        private Component createButtonsLayout() { // botoes do formulario
            save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
            close.addThemeVariants(ButtonVariant.LUMO_TERTIARY); //tema
            close.addClassName("green-link");

            save.addClickShortcut(Key.ENTER); //atalho
            close.addClickShortcut(Key.ESCAPE);

            save.addClickListener(event -> validateAndSave()); //metoodo para salvar
            delete.addClickListener(event -> fireEvent(new com.example.application.entidade.FormCadastroE.DeleteEvent(this, binder.getBean())));
            close.addClickListener(event -> fireEvent(new com.example.application.entidade.FormCadastroE.CloseEvent(this)));
            binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid())); //ativa/desativa a aptidao do botao
            return new HorizontalLayout(save, delete, close);

        }

        private void validateAndSave() {
            if (binder.isValid()) {
                fireEvent(new com.example.application.entidade.FormCadastroE.SaveEvent(this, binder.getBean()));
            }
        }


        // Events
        public static abstract class ContactFormEvent extends ComponentEvent<com.example.application.entidade.FormCadastroE> {
            private final Eletivas eletiva;

            protected ContactFormEvent(com.example.application.entidade.FormCadastroE source, Eletivas eletiva) {
                super(source, false);
                this.eletiva = eletiva;
            }

            public Eletivas getContact() {
                return eletiva;
            }
        }

        public static class SaveEvent extends com.example.application.entidade.FormCadastroE.ContactFormEvent {
            SaveEvent(com.example.application.entidade.FormCadastroE source, Eletivas eletiva) {
                super(source, eletiva);
            }
        }

        public static class DeleteEvent extends com.example.application.entidade.FormCadastroE.ContactFormEvent {
            DeleteEvent(com.example.application.entidade.FormCadastroE source, Eletivas eletiva) {
                super(source, eletiva);
            }

        }

        public static class CloseEvent extends com.example.application.entidade.FormCadastroE.ContactFormEvent {
            CloseEvent(com.example.application.entidade.FormCadastroE source) {
                super(source, null);
            }
        }

        public Registration addDeleteListener(ComponentEventListener<com.example.application.entidade.FormCadastroE.DeleteEvent> listener) {
            return addListener(com.example.application.entidade.FormCadastroE.DeleteEvent.class, listener);
        }

        public Registration addSaveListener(ComponentEventListener<com.example.application.entidade.FormCadastroE.SaveEvent> listener) {
            return addListener(com.example.application.entidade.FormCadastroE.SaveEvent.class, listener);
        }
        public Registration addCloseListener(ComponentEventListener<com.example.application.entidade.FormCadastroE.CloseEvent> listener) {
            return addListener(com.example.application.entidade.FormCadastroE.CloseEvent.class, listener);
        }
    }

