package com.example.application.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;


@RolesAllowed("ROLE_ALUNO")
@Route (value = "aluno-home", layout = LayoutPrincipalAluno.class)
@PageTitle("Home Aluno | Trilhas EletivasView ")


public class HomeViewAluno extends VerticalLayout {


        public HomeViewAluno() {
            setAlignItems(Alignment.CENTER);
            //Image img = new Image("images/a.png", "placeholder plant");
            //img.addClassName("home");
            //add(img);
        }
    }

