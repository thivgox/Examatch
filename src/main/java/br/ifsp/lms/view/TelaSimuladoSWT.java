package br.ifsp.lms.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import br.ifsp.lms.model.Simulado;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TelaSimuladoSWT {

    public static List<Simulado> bancoFakeSimulados = new ArrayList<>();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("LMS Exatas - Gestao de Simulados");
        shell.setSize(500, 450);
        shell.setLayout(new GridLayout(2, false));

        Label lblTituloCad = new Label(shell, SWT.NONE);
        lblTituloCad.setText("CADASTRAR SIMULADO");
        lblTituloCad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        new Label(shell, SWT.NONE).setText("ID:");
        Text txtId = new Text(shell, SWT.BORDER);
        txtId.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        new Label(shell, SWT.NONE).setText("Titulo:");
        Text txtTitulo = new Text(shell, SWT.BORDER);
        txtTitulo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        new Label(shell, SWT.NONE).setText("Data de Criacao (dd/MM/yyyy):");
        Text txtDataCriacao = new Text(shell, SWT.BORDER);
        txtDataCriacao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Button btnSalvar = new Button(shell, SWT.PUSH);
        btnSalvar.setText("Salvar Simulado");
        btnSalvar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        Label separator = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        Label lblTituloCons = new Label(shell, SWT.NONE);
        lblTituloCons.setText("SIMULADOS CADASTRADOS");
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
                if(txtId.getText().isEmpty() || txtTitulo.getText().isEmpty() || txtDataCriacao.getText().isEmpty()) {
                    MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING);
                    mb.setMessage("Por favor, preencha todos os campos!");
                    mb.open();
                    return;
                }

                try {
                    int id = Integer.parseInt(txtId.getText());
                    Date dataCriacao = DATE_FORMAT.parse(txtDataCriacao.getText());
                    Simulado novoSimulado = new Simulado(id, txtTitulo.getText(), dataCriacao);
                    bancoFakeSimulados.add(novoSimulado);

                    MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION);
                    mb.setMessage("Simulado cadastrado com sucesso!");
                    mb.open();

                    txtId.setText("");
                    txtTitulo.setText("");
                    txtDataCriacao.setText("");
                } catch (NumberFormatException ex) {
                    MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR);
                    mb.setMessage("ID deve ser um número inteiro válido.");
                    mb.open();
                } catch (ParseException ex) {
                    MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR);
                    mb.setMessage("Formato de data inválido. Use dd/MM/yyyy.");
                    mb.open();
                }
            }
        });

        btnAtualizar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                listConsulta.removeAll();
                
                for (Simulado s : bancoFakeSimulados) {
                    listConsulta.add("ID: " + s.getId() + " | " + s.getTitulo() + " | " + DATE_FORMAT.format(s.getDataCriacao()));
                }
                
                if(bancoFakeSimulados.isEmpty()) {
                    listConsulta.add("Nenhum simulado registrado no sistema.");
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
