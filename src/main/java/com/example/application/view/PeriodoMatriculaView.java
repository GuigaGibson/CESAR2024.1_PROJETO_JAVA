package com.example.application.view;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "periodomatricula", layout = LayoutPrincipal.class)
@PageTitle("Período Matricula | Trilhas Eletivas")

public class PeriodoMatriculaView extends VerticalLayout {
}
