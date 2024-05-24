package com.example.application.provider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String redirectUrl = "/default";
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ALUNO"))) {
            redirectUrl = "/aluno/home";
        }  else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_TROCAR_SENHA"))){
            redirectUrl = "/trocarSenha";
        }
        else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_GESTOR"))) {
            redirectUrl = "/gestor/home";
        }
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}