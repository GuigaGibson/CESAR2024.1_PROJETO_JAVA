package com.example.application.apresentacao.eletivas;

import com.example.application.apresentacao.LayoutPrincipal;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "eletivas", layout = LayoutPrincipal.class)
@PageTitle("Eletivas | Trilhas EletivasView ")
public class EletivasView extends Div {

    public EletivasView() {
        // Exemplo de eletivas
        Eletiva eletiva1 = new Eletiva("Fotografia Digital", "Nesta eletiva, os alunos aprenderão os princípios básicos da fotografia digital, incluindo composição, iluminação e edição de imagens. Além disso, explorarão o uso de câmeras digitais e softwares de edição para criar e aperfeiçoar suas próprias fotografias.\nMaria Silva");
        Eletiva eletiva2 = new Eletiva("Introdução à Programação", "Esta eletiva oferece uma introdução ao mundo da programação de computadores. Os alunos aprenderão conceitos fundamentais de lógica de programação e desenvolverão habilidades práticas utilizando a linguagem Python para criar pequenos programas e jogos.\nJoão Pereira");
        Eletiva eletiva3 = new Eletiva("Teatro e Expressão Corporal", "Os alunos terão a oportunidade de explorar o teatro como forma de expressão artística. Através de exercícios de expressão corporal, improvisação e leitura de textos dramáticos, os participantes desenvolverão habilidades de comunicação, criatividade e trabalho em equipe.\nAna Rodrigues");

        // Adiciona as eletivas à tela
        add(eletiva1, eletiva2, eletiva3);
    }

    public class Eletiva extends Div {
        public Eletiva(String nome, String descricaoDetalhada) {
            addClassName("eletiva-box");

            Paragraph nomeLabel = new Paragraph(nome);
            Button linkButton = new Button("Detalhes");

            // Cria o Dialog para mostrar mais detalhes
            Dialog detailsDialog = new Dialog();

            // Divide a descrição detalhada em parágrafos usando a quebra de linha como delimitador
            String[] descricaoPartes = descricaoDetalhada.split("\n");
            for (String parte : descricaoPartes) {
                detailsDialog.add(new Paragraph(parte));
            }

            // Cria o botão "Cadastrar"
            Button cadastrarButton = new Button("Cadastrar");

            // Adiciona o botão "Cadastrar" ao Dialog
            detailsDialog.add(cadastrarButton);

            // Configura o botão para abrir o Dialog ao invés de redirecionar
            linkButton.addClickListener(e -> detailsDialog.open());

            linkButton.addClassName("black-link");
            add(nomeLabel, linkButton);
        }
    }
}
