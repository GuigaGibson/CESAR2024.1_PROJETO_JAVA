package com.example.application.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route (value = "", layout = LayoutPrincipalGestor.class)
@PageTitle("Home | Trilhas EletivasView ")

public class HomeView extends VerticalLayout {

    public HomeView() {
        setAlignItems(Alignment.CENTER);
        //Image img = new Image("images/a.png", "placeholder plant");
        //img.addClassName("home");
        //add(img);
    }



}
