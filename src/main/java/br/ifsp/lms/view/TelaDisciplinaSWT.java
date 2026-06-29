package br.ifsp.lms.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import br.ifsp.lms.model.Disciplina;
import java.util.ArrayList;
import java.util.List;

public class TelaDisciplinaSWT {

    public static List<Disciplina> bancoFakeDisciplinas = new ArrayList<>();

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("LMS Exatas - Gestao de Disciplinas");
        shell.setSize(500, 450);
        shell.setLayout(new GridLayout(2, false));

        Label lblTituloCad = new Label(shell, SWT.NONE);
        lblTituloCad.setText("CADASTRAR DISCIPLINA");
        lblTituloCad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        new Label(shell, SWT.NONE).setText("ID:");
        Text txtId = new Text(shell, SWT.BORDER);
        txtId.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        new Label(shell, SWT.NONE).setText("Nome:");
        Text txtNome = new Text(shell, SWT.BORDER);
        txtNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        new Label(shell, SWT.NONE).setText("Ementa:");
        Text txtEmenta = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        GridData gdEmenta = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gdEmenta.heightHint = 60;
        txtEmenta.setLayoutData(gdEmenta);

        Button btnSalvar = new Button(shell, SWT.PUSH);
        btnSalvar.setText("Salvar Disciplina");
        btnSalvar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        Label separator = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        Label lblTituloCons = new Label(shell, SWT.NONE);
        lblTituloCons.setText("DISCIPLINAS CADASTRADAS");
        lblTituloCons.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        org.eclipse.swt.widgets.List listConsulta = new org.eclipse.swt.widgets.List(shell, SWT.BORDER | SWT.V_SCROLL);
        GridData gridDataList = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
        gridDataList.heightHint = 100;
        listConsulta.setLayoutData(gridDataList);

        Button btnAtualizar = new Button(shell, SWT.PUSH);
        btnAtualizar.setText("Atualizar Lista / Consultar");
        btnAtualizar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        btnSalvar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(txtId.getText().isEmpty() || txtNome.getText().isEmpty() || txtEmenta.getText().isEmpty()) {
                    MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING);
                    mb.setMessage("Por favor, preencha todos os campos!");
                    mb.open();
                    return;
                }

                try {
                    int id = Integer.parseInt(txtId.getText());
                    Disciplina novaDisciplina = new Disciplina(id, txtNome.getText(), txtEmenta.getText());
                    bancoFakeDisciplinas.add(novaDisciplina);

                    MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION);
                    mb.setMessage("Disciplina cadastrada com sucesso!");
                    mb.open();

                    txtId.setText("");
                    txtNome.setText("");
                    txtEmenta.setText("");
                } catch (NumberFormatException ex) {
                    MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR);
                    mb.setMessage("ID deve ser um número inteiro válido.");
                    mb.open();
                }
            }
        });

        btnAtualizar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                listConsulta.removeAll();
                
                for (Disciplina d : bancoFakeDisciplinas) {
                    listConsulta.add("ID: " + d.getId() + " | " + d.getNome());
                }
                
                if(bancoFakeDisciplinas.isEmpty()) {
                    listConsulta.add("Nenhuma disciplina registrada no sistema.");
                }
            }
        });

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
