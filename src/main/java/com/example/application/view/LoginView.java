
package com.example.application.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route("login")
    @PageTitle("Login | Trilha de EletivasView")

    public class LoginView extends VerticalLayout implements BeforeEnterObserver {

        private final LoginForm login = new LoginForm();

        public LoginView(){
            addClassName("login-view");
            setSizeFull();
            setAlignItems(Alignment.CENTER);
            setJustifyContentMode(JustifyContentMode.CENTER);

            login.setAction("login");

            add(new H1("TRILHAS ELETIVAS"), login);
        }

        @Override
        public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
            // inform the user about an authentication error
            if(beforeEnterEvent.getLocation()
                    .getQueryParameters()
                    .getParameters()
                    .containsKey("error")) {
                login.setError(true);
            }
        }
    }
