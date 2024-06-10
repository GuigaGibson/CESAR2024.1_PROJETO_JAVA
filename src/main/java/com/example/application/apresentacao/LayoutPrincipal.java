package com.example.application.apresentacao;

import com.example.application.apresentacao.aluno.HomeViewA;
import com.example.application.apresentacao.aluno.LayoutAluno;
import com.example.application.apresentacao.gestor.HomeViewG;
import com.example.application.apresentacao.gestor.LayoutGestor;
import com.example.application.negocio.security.SecurityService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        VaadinSession.getCurrent().access(() -> {
            if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_TROCAR_SENHA"))) UI.getCurrent().getPage().executeJs("window.location.href=$0", new RouterLink("Sou Aluno", HomeViewA.class).getHref());
            else UI.getCurrent().getPage().executeJs("window.location.href=$0", new RouterLink("Sou Gestor", HomeViewG.class).getHref());
        });
    }
}
