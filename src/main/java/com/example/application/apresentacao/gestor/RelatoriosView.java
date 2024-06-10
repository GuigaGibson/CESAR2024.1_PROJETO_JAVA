package com.example.application.apresentacao.gestor;

import com.example.application.apresentacao.gestor.LayoutGestor;
import com.example.application.persistencia.RegistrationServiceA;
import com.example.application.entidade.Aluno;
import com.example.application.entidade.Eletivas;
import com.example.application.persistencia.RegistrationServiceE;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import jakarta.annotation.security.PermitAll;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@PermitAll
@Route(value = "relatorios", layout = LayoutGestor.class)
public class RelatoriosView extends VerticalLayout {

    private final RegistrationServiceA alunoService;
    private final RegistrationServiceE eletivaService;

    public RelatoriosView(RegistrationServiceA alunoService, RegistrationServiceE eletivaService) {
        this.alunoService = alunoService;
        this.eletivaService = eletivaService;

        Button exportAlunosButton = new Button("Exportar Alunos para Excel");
        exportAlunosButton.getStyle().set("background-color", "green");
        exportAlunosButton.getStyle().set("color", "white");
        Button exportEletivasButton = new Button("Exportar Eletivas para Excel");
        exportEletivasButton.getStyle().set("background-color", "green");
        exportEletivasButton.getStyle().set("color", "white");

        add(exportAlunosButton, exportEletivasButton);

        exportAlunosButton.addClickListener(e -> {
            try {
                ByteArrayOutputStream outputStream = exportAlunos();
                StreamResource resource = new StreamResource("alunos.xlsx", () -> new ByteArrayInputStream(outputStream.toByteArray()));
                configureDownloadLink(resource, "Baixar Relatório de Alunos");
                Notification.show("Download do arquivo de alunos iniciado.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        exportEletivasButton.addClickListener(e -> {
            try {
                ByteArrayOutputStream outputStream = exportEletivas();
                StreamResource resource = new StreamResource("eletivas.xlsx", () -> new ByteArrayInputStream(outputStream.toByteArray()));
                configureDownloadLink(resource, "Baixar Relatório de Eletivas");
                Notification.show("Download do arquivo de eletivas iniciado.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        setAlignItems(Alignment.CENTER);
    }

    private ByteArrayOutputStream exportAlunos() throws IOException {
        List<Aluno> alunos = alunoService.getAllAlunos();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Alunos");
            createHeaderRow(sheet, "Matrícula", "Nome", "Email", "Senha", "Serie", "Turma", "Eletiva");

            int rowNum = 1;
            for (Aluno aluno : alunos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(aluno.getMatricula());
                row.createCell(1).setCellValue(aluno.getNome());
                row.createCell(2).setCellValue(aluno.getEmail());
                row.createCell(3).setCellValue(aluno.getSenha());
                row.createCell(4).setCellValue(aluno.getSerie());
                row.createCell(5).setCellValue(aluno.getTurma());
                // Verifica se a eletiva do aluno não é nula antes de tentar acessar seu nome
                String eletivaNome = aluno.getEletiva() != null ? aluno.getEletiva().getNome() : "";
                row.createCell(6).setCellValue(eletivaNome);
            }

            autoSizeColumns(sheet, 7);
            workbook.write(out);
        }
        return out;
    }

    private ByteArrayOutputStream exportEletivas() throws IOException {
        List<Eletivas> eletivas = eletivaService.getAllEletivas();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Eletivas");
            createHeaderRow(sheet, "Nome", "Descricao", "Professor");

            int rowNum = 1;
            for (Eletivas eletiva : eletivas) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(eletiva.getNome());
                row.createCell(1).setCellValue(eletiva.getDescricao());
                row.createCell(2).setCellValue(eletiva.getProfessor());
            }

            autoSizeColumns(sheet, 3);
            workbook.write(out);
        }
        return out;
    }

    private void createHeaderRow(Sheet sheet, String... headers) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(createHeaderCellStyle(sheet.getWorkbook()));
        }
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerCellStyle.setFont(font);
        return headerCellStyle;

    }

    private void autoSizeColumns(Sheet sheet, int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }
    private void configureDownloadLink(StreamResource resource, String buttonText) {
        com.vaadin.flow.component.html.Anchor downloadLink = new com.vaadin.flow.component.html.Anchor(resource, buttonText);
        downloadLink.getElement().setAttribute("download", true);
        downloadLink.getStyle().set("color", "black");
        downloadLink.addClassName("conf");

        this.add(downloadLink);
    }

}
