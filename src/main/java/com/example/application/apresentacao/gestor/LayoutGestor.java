package com.example.application.apresentacao.gestor;

import com.example.application.apresentacao.gestor.CadastroEView;
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

public class LayoutGestor extends AppLayout{

    private final SecurityService securityService;

    public LayoutGestor(SecurityService securityService) {
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
        RouterLink homeG = new RouterLink("Home", HomeViewG.class);
        RouterLink cadastroLink = new RouterLink("Cadastro de Alunos", CadastroAView.class);
        RouterLink cadastroELink = new RouterLink("Cadastro de Eletivas", CadastroEView.class);
        RouterLink manipularLink = new RouterLink("Manipular Usuários", ManipularAView.class);

        RouterLink periodoLink = new RouterLink("Período de Matricula", CadastroPView.class);
        RouterLink relatoriosLink = new RouterLink("Relatórios", RelatoriosView.class);

        RouterLink perfilLink = new RouterLink("Perfil do Gestor", PerfilGestorView.class);

        // Adiciona a classe CSS personalizada aos links
        homeG.addClassName("black-link");
        cadastroLink.addClassName("black-link");
        cadastroELink.addClassName("black-link");
        manipularLink.addClassName("black-link");
        periodoLink.addClassName("black-link");

        relatoriosLink.addClassName("black-link");

        perfilLink.addClassName("black-link");
        periodoLink.addClassName("black-link");

        VerticalLayout drawer = new VerticalLayout(
                homeG,
                cadastroLink,
                cadastroELink,
                manipularLink,

                periodoLink,

                relatoriosLink,

                perfilLink
        );

        // Adiciona o drawer ao layout principal
        addToDrawer(drawer);
    }
}



