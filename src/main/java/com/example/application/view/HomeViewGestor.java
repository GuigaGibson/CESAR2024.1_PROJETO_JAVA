package com.example.application.view;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;

@RolesAllowed ("ROLE_GESTOR")
@Route(value = "gestor-home", layout = LayoutPrincipalGestor.class)
@PageTitle("Home Gestor | Trilhas EletivasView ")

public class HomeViewGestor {

  public HomeViewGestor(){
      setAlignItems(Alignment.CENTER);
      //Image img = new Image("images/a.png", "placeholder plant");
      //img.addClassName("home");
      //add(img);
  }

}


