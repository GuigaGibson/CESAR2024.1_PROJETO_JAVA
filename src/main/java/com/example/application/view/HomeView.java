package com.example.application.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;

@PermitAll
@Route (value = "", layout = LayoutPrincipal.class)
@PageTitle("Home | Trilhas EletivasView ")

public class HomeView extends VerticalLayout {

    public String homeALuno() {
        return "homeAlunoView";
    }


    public HomeView() {
        setAlignItems(Alignment.CENTER);
        //Image img = new Image("images/a.png", "placeholder plant");
        //img.addClassName("home");
        //add(img);
    }



}
