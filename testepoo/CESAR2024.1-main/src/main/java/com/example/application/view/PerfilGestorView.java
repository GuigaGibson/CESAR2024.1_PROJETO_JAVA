package com.example.application.view;


import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "perfilgestor", layout = LayoutPrincipal.class)
@PageTitle ("Perfil | Trilhas Eletivas")

public class PerfilGestorView extends VerticalLayout {

    public PerfilGestorView() {

        Image img = new Image("images/design.jpg", "placeholder plant");
        //setSizeFull();
        //setAlignItems(Alignment.CENTER);
        //img.addClassName("img-perfil");
        //add(img);
    }


    }
