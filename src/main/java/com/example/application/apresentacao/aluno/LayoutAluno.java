package com.example.application.apresentacao.aluno;




import com.example.application.apresentacao.eletivas.EletivasView;
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

public class LayoutAluno extends AppLayout {


    private final SecurityService securityService;

    public LayoutAluno(SecurityService securityService) {
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
        RouterLink homeA = new RouterLink("Home", HomeViewA.class);
        RouterLink eletivas = new RouterLink("Eletivas", EletivasView.class);
        RouterLink perfilAluno = new RouterLink("Perfil do aluno", PerfilAlunoView.class);

        // Adiciona a classe CSS personalizada aos links
        homeA.addClassName("black-link");
        eletivas.addClassName("black-link");
        perfilAluno.addClassName("black-link");

        VerticalLayout drawer = new VerticalLayout(
                homeA,
                eletivas,
                perfilAluno
                //relatoriosLink,

        );

        // Adiciona o drawer ao layout principal
        addToDrawer(drawer);
    }
}