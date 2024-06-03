package com.example.application.apresentacao;

import com.example.application.apresentacao.aluno.HomeViewA;
import com.example.application.apresentacao.gestor.HomeViewG;
import com.example.application.negocio.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class LayoutPrincipal extends AppLayout {


    private final SecurityService securityService;

    public LayoutPrincipal(SecurityService securityService) {
        this.securityService = securityService;

        createHeader(); // cria cabeçalho
        createDrawer(); // cria menu vertical

    }

    private void createHeader() {
        H1 logo = new H1("TRILHAS ELETIVAS");
        logo.addClassNames(
                LumoUtility.FontSize.XLARGE,
                LumoUtility.Margin.MEDIUM,
                "cor");

        // Criar o DrawerToggle e aplicar o estilo personalizado

        DrawerToggle barrinha = new DrawerToggle();
        barrinha.addClassNames("cor");


        String u = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + u, e -> securityService.logout());
        var header = new HorizontalLayout(barrinha, logo, logout);

        logo.addClassNames("cor");
        logout.addClassName("cor");

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM,
                "add-navbar");

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink homeA = new RouterLink("Sou Aluno", HomeViewA.class);
        RouterLink homeG = new RouterLink("Sou Gestor", HomeViewG.class);
        // Adiciona a classe CSS personalizada aos links
        homeA.addClassName("black-link");
        homeG.addClassName("black-link");


        VerticalLayout drawer = new VerticalLayout(
                homeA,
                homeG
                //relatoriosLink,

        );

        // Adiciona o drawer ao layout principal
        addToDrawer(drawer);
    }
}
