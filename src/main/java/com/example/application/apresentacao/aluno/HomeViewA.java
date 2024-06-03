package com.example.application.apresentacao.aluno;

import com.example.application.apresentacao.HomeView;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

@PermitAll
@Route (value = "homeA", layout = LayoutAluno.class)
@PageTitle("HomeView | Trilhas EletivasView ")


@RolesAllowed("ROLE_TROCAR_SENHA")
public class HomeViewA extends HomeView {

    public HomeViewA() {}
}
