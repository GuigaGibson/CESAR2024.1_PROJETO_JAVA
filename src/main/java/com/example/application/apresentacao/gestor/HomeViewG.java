package com.example.application.apresentacao.gestor;

import com.example.application.apresentacao.HomeView;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;


@PermitAll
@Route(value = "homeg", layout = LayoutGestor.class)
@PageTitle("HomeView | Trilhas EletivasView ")


@RolesAllowed("ROLE_GESTOR")
public class HomeViewG extends HomeView {

    public HomeViewG() {}

}
