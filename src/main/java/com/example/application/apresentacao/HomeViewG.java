package com.example.application.apresentacao;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


@PermitAll
@Route(value = "homeg", layout = LayoutGestor.class)
@PageTitle("Home | Trilhas EletivasView ")

public class HomeViewG extends VerticalLayout {

    public HomeViewG() {
        setAlignItems(Alignment.START);

        Image img = new Image("images/trilhas.png", "placeholder plant");
        img.addClassName("home");
        add(img);
    }



}
