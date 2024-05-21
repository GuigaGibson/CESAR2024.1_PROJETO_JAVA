package com.example.application.view;

import com.example.application.data.AlunoRepository;
import com.example.application.entity.Aluno;
import com.example.application.data.services.RegistrationServiceA;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


@StyleSheet("frontend://src/styles.css")
@PermitAll
@Route(value = "cadastro-aluno")
public class CadastroAluno extends VerticalLayout {

    private final RegistrationServiceA serviceA;
    private final AlunoRepository repoA;

    private Binder<Aluno> binder = new BeanValidationBinder<>(Aluno.class);

    private TextField matricula = new TextField("Matricula");
    private TextField nome = new TextField("Nome");
    private TextField email = new TextField("Email");
    private PasswordField senha = new PasswordField("Senha");
    private ComboBox<String> serie = new ComboBox<>("Serie");
    private ComboBox<String> turma = new ComboBox<>("Turma");


    private Grid<Aluno> alunos = new Grid<>(Aluno.class);

    public CadastroAluno(RegistrationServiceA serviceA, AlunoRepository repoA) {
        this.serviceA = serviceA;
        this.repoA = repoA;

        // Build the layout
        H1 heading = new H1("Cadastro de Alunos");
        Button submit = new Button("Cadastrar");
        setDefaultHorizontalComponentAlignment(FlexLayout.Alignment.CENTER);
        add(heading, matricula, nome, email, senha, serie, turma, submit);

        // Configure components
        serie.setItems(serviceA.getSerie());
        turma.setItems(serviceA.getTurma());

        submit.addClickListener(e -> {
            submitOrder();
            String msg = String.format(
                    "Thank you %s, your registration was submitted!",
                    binder.getBean().getNome());
            Notification.show(msg, 3000, Notification.Position.MIDDLE);
            init();
            listOrders();
        });

        // Add keyboard shortcut
        submit.addClickShortcut(Key.ENTER);

        // Bind fields from this UI class to domain object using naming convention
        binder.bindInstanceFields(this);
        // Enable save button only if the bean is valid
        binder.addStatusChangeListener(e -> submit.setEnabled(binder.isValid()));

        init();

        // Configure the grid
        alunos.setColumns("matricula", "nome","email", "senha","serie", "turma");
        alunos.addComponentColumn(order -> {
            Button deleteBtn = new Button(VaadinIcon.TRASH.create());
            deleteBtn.addClickListener(e -> {
                repoA.delete(order);
                listOrders();
            });
            return deleteBtn;
        });

        Button update = new Button("Update List", VaadinIcon.REFRESH.create());
        update.addClickListener(e -> listOrders());

        add(alunos, update);

        listOrders();
    }

    private void submitOrder() {
        serviceA.register(binder.getBean());
    }

    private void init() {
        binder.setBean(new Aluno());
    }

    public void listOrders() {
        alunos.setItems(repoA.findAll());
    }
}
