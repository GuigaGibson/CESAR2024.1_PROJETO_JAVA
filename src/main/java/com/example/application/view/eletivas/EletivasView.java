package com.example.application.view.eletivas;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route("eletivas")
public class EletivasView extends Div {

    public EletivasView() {
        // Exemplo de eletivas
        Eletiva eletiva1 = new Eletiva("Eletiva 1", "Descrição da Eletiva 1", Eletiva1View.class);
        Eletiva eletiva2 = new Eletiva("Eletiva 2", "Descrição da Eletiva 2", Eletiva2View.class);
        Eletiva eletiva3 = new Eletiva("Eletiva 3", "Descrição da Eletiva 3", Eletiva3View.class);

        // Adiciona as eletivas à tela
        add(eletiva1, eletiva2, eletiva3);
    }

    public class Eletiva extends Div {
        public Eletiva(String nome, String descricao, Class<?> viewClass) {
            addClassName("eletiva-box");

            Paragraph nomeLabel = new Paragraph(nome);
            Paragraph descricaoLabel = new Paragraph(descricao);
            Button linkButton = new Button("Detalhes");
            linkButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate(String.valueOf(viewClass))));

            linkButton.addClassName("black-link");
            add(nomeLabel, descricaoLabel, linkButton);
        }
    }
}
