/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.example.application.entity;

import com.example.application.data.AlunoRepository;
import com.example.application.view.LayoutPrincipal;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


@PermitAll
@Route (value= "form-aluno", layout = LayoutPrincipal.class)
public class FormCadastroA extends HorizontalLayout {
    
    private final AlunoRepository repoA;
    private final Grid<Aluno> alunos = new Grid<>(Aluno.class);

    
    public FormCadastroA(AlunoRepository repoA) {
        this.repoA = repoA;
        // Build the layout

        H1 heading = new H1("Lista de Cadastro");
        Button update = new Button(VaadinIcon.REFRESH.create());
        add(heading, update, alunos);
        setSizeFull();
        
        alunos.setColumns("matricula", "nome", "email", "senha", "serie", "turma");
        alunos.addComponentColumn(order -> {
            Button deleteBtn = new Button(VaadinIcon.TRASH.create());
            deleteBtn.addClickListener(e -> {
                repoA.delete(order);
                listOrders();
            });
            return deleteBtn;
        });
        listOrders();
        
        update.addClickListener(e -> listOrders());
        
    }

    public void listOrders() {
        alunos.setItems(repoA.findAll());
    }
    
}
