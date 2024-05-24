package com.example.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import jakarta.annotation.security.PermitAll;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@PermitAll
@SpringBootApplication
@Theme(value = "flowcrmtutorial")
@PWA(
        name = "Trilha de Eletivas",
        shortName = "ELT",
        offlinePath="offline.html",
        offlineResources = { "./images/offline.png", "./icons/logo.png"}
        )

public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
