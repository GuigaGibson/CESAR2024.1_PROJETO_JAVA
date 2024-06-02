package com.example.application.apresentacao.eletivas;

import com.example.application.apresentacao.LayoutPrincipal;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


@PermitAll
@Route(value = "", layout = LayoutPrincipal.class)
@PageTitle("Home | Trilhas EletivasView ")

public class Home extends VerticalLayout {

    public Home() {
        setAlignItems(Alignment.START);

        Image img = new Image("images/trilhas.png", "placeholder plant");
        img.addClassName("home");
        add(img);
    }



}
