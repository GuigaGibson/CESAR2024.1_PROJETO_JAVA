package com.example.application.view;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route (value = "manipularA", layout = LayoutPrincipalAluno.class)
@PageTitle("Manipular Usuários | Trilhas Eletivas")

public class ManipularAView extends VerticalLayout {
}
