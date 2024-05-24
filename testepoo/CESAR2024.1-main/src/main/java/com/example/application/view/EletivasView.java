package com.example.application.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


@PermitAll
@Route(value = "eletivas", layout = LayoutPrincipal.class)
@PageTitle("Eletivas | Trilhas Eletivas")


public class EletivasView extends VerticalLayout {

}
