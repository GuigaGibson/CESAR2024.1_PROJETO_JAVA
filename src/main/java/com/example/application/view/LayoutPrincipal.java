package com.example.application.view;

import com.example.application.security.SecurityService;
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
        RouterLink homeLink = new RouterLink("Home", HomeView.class);
        RouterLink cadastroLink = new RouterLink("Cadastro de Alunos", CadastroAluno.class);
        RouterLink eletivasLink = new RouterLink("Eletivas", EletivasView.class);
        RouterLink manipularLink = new RouterLink("Manipular Usuários", ManipularAView.class);
        RouterLink periodoLink = new RouterLink("Período de Matricula", PeriodoMatriculaView.class);
        //RouterLink relatoriosLink = new RouterLink("Relatórios", RelatorioView.class);
        RouterLink perfilLink = new RouterLink("Perfil", PerfilGestorView.class);

        // Adiciona a classe CSS personalizada aos links
        homeLink.addClassName("black-link");
        cadastroLink.addClassName("black-link");
        eletivasLink.addClassName("black-link");
        manipularLink.addClassName("black-link");
        periodoLink.addClassName("black-link");
        //relatoriosLink.addClassName("black-link");
        perfilLink.addClassName("black-link");

        VerticalLayout drawer = new VerticalLayout(
                homeLink,
                cadastroLink,
                eletivasLink,
                manipularLink,
                periodoLink,
                //relatoriosLink,
                perfilLink
        );

        // Adiciona o drawer ao layout principal
        addToDrawer(drawer);
    }
}
