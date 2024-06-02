package com.example.application.apresentacao;


import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "perfilaluno", layout = LayoutGestor.class)
@PageTitle ("Perfil | Trilhas Eletivas")

public class PerfilAlunoView extends VerticalLayout {

    public PerfilAlunoView() {

        Image img = new Image("images/design.jpg", "placeholder plant");
        //setSizeFull();
        //setAlignItems(Alignment.CENTER);
        //img.addClassName("img-perfil");
        //add(img);
    }


}
