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
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.LARGE);

        String u = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + u, e -> securityService.logout());

        var header = new HorizontalLayout(new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);

    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout(
                new RouterLink("Home", HomeView.class),
                new RouterLink("Cadastrar alunos", CadastroAView.class),
                new RouterLink("Eletivas", HomeView.class),
                new RouterLink("Manipular usuários", HomeView.class),
                new RouterLink("Período de matrícula", HomeView.class),
                new RouterLink("Relatórios", RelatorioView.class),
                new RouterLink("Perfil", HomeView.class)
        ));
    }
}