package com.example.application.apresentacao;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route (value = "homeA", layout = LayoutAluno.class)
@PageTitle("Home | Trilhas EletivasView ")

public class HomeViewA extends VerticalLayout {

    public HomeViewA() {
        setAlignItems(Alignment.START);

        Image img = new Image("images/trilhas.png", "eletivas");
        img.addClassName("home");
        add(img);
    }



}
