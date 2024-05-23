package com.example.application.entity;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ImageBox extends Div {

    public ImageBox(String title, String imageUrl, String description) {
        // Cria uma imagem com a URL fornecida
        Image image = new Image(imageUrl, "Imagem");
        image.setWidth("100%"); // Define a largura da imagem como 100%

        // Cria um parágrafo com o título
        Paragraph titleParagraph = new Paragraph(title);
        titleParagraph.getStyle().set("font-size", "20px"); // Define o tamanho da fonte do título

        // Cria um parágrafo com a descrição
        Paragraph descriptionParagraph = new Paragraph(description);
        descriptionParagraph.getStyle().set("color", "gray"); // Define a cor do texto da descrição

        // Adiciona a imagem, o título e a descrição ao layout vertical
        VerticalLayout layout = new VerticalLayout(image, titleParagraph, descriptionParagraph);
        layout.setPadding(true); // Adiciona preenchimento ao layout
        layout.setSpacing(true); // Adiciona espaçamento entre os componentes

        // Adiciona o layout ao componente ImageBox
        add(layout);

        // Adiciona estilo CSS ao componente ImageBox
        getStyle().set("border", "1px solid #ccc"); // Adiciona uma borda ao redor da caixa
        getStyle().set("border-radius", "5px"); // Adiciona uma borda arredondada à caixa
        setWidth("300px"); // Define a largura da caixa como 300 pixels
    }
}
