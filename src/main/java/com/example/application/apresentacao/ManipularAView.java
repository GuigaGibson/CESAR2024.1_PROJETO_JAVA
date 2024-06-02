package com.example.application.apresentacao;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route (value = "aluno/home", layout = LayoutGestor.class)
@PageTitle("Manipular Usuários | Trilhas Eletivas")

public class ManipularAView extends VerticalLayout {
}
