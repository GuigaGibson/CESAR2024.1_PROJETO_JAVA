package com.example.application.entity;

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

import java.util.List;


@PermitAll
public class Form extends FormLayout {

    BeanValidationBinder<Aluno> binder = new BeanValidationBinder<>(Aluno.class);

    public void setContact(Aluno aluno) {
        binder.setBean(aluno);
    }
    TextField matricula = new TextField("Matrícula");
    TextField nome = new TextField("Nome");
    TextField eletiva = new TextField("Eletiva");
    ComboBox<Serie> serie = new ComboBox<>("Serie");
    ComboBox<Turma> turma = new ComboBox<>("Turma");


    Button save = new Button("Salvar");
    Button delete = new Button("Deletar");
    Button close = new Button("Cancelar");


    public Form(List<Serie> series, List<Turma> turmas) {
        addClassName("aluno-form");
        binder.bindInstanceFields(this);
        serie.setItems(series);
        serie.setItemLabelGenerator(Serie::getName);
        turma.setItems(turmas);
        turma.setItemLabelGenerator(Turma::getName);

        add(matricula,
                nome,
                eletiva,
                serie,
                turma,
                createButtonsLayout());
    }



    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

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







// Events
public static abstract class ContactFormEvent extends ComponentEvent<Form> {
    private final Aluno aluno;

    protected ContactFormEvent(Form source, Aluno aluno) {
        super(source, false);
        this.aluno = aluno;
    }

    public Aluno getContact() {
        return aluno;
    }
}

public static class SaveEvent extends ContactFormEvent {
    SaveEvent(Form source, Aluno aluno) {
        super(source, aluno);
    }
}

public static class DeleteEvent extends ContactFormEvent {
    DeleteEvent(Form source, Aluno aluno) {
        super(source, aluno);
    }

}

public static class CloseEvent extends ContactFormEvent {
    CloseEvent(Form source) {
        super(source, null);
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
