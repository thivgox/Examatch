package br.ifsp.lms.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import br.ifsp.lms.model.TentativaSimulado;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TelaTentativaSimuladoSWT {

    public static List<TentativaSimulado> bancoFakeTentativas = new ArrayList<>();

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("LMS Exatas - Registro de Tentativas de Simulado");
        shell.setSize(500, 450);
        shell.setLayout(new GridLayout(2, false));

        Label lblTituloCad = new Label(shell, SWT.NONE);
        lblTituloCad.setText("REGISTRAR TENTATIVA DE SIMULADO");
        lblTituloCad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        new Label(shell, SWT.NONE).setText("Tempo Gasto (segundos):");
        Text txtTempo = new Text(shell, SWT.BORDER);
        txtTempo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        new Label(shell, SWT.NONE).setText("Nota Obtida:");
        Text txtNota = new Text(shell, SWT.BORDER);
        txtNota.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Button btnSalvar = new Button(shell, SWT.PUSH);
        btnSalvar.setText("Salvar Tentativa");
        btnSalvar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        Label separator = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        Label lblTituloCons = new Label(shell, SWT.NONE);
        lblTituloCons.setText("TENTATIVAS REGISTRADAS");
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
                if(txtTempo.getText().isEmpty() || txtNota.getText().isEmpty()) {
                    MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING);
                    mb.setMessage("Por favor, preencha todos os campos!");
                    mb.open();
                    return;
                }

                try {
                    int tempo = Integer.parseInt(txtTempo.getText());
                    double nota = Double.parseDouble(txtNota.getText());

                    TentativaSimulado novaTentativa = new TentativaSimulado();
                    novaTentativa.setId(bancoFakeTentativas.size() + 1);
                    novaTentativa.setTempoGastoSegundos(tempo);
                    novaTentativa.setNotaObtida(nota);
                    novaTentativa.setDataRealizacao(new Date());

                    bancoFakeTentativas.add(novaTentativa);

                    MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION);
                    mb.setMessage("Tentativa registrada com sucesso!");
                    mb.open();

                    txtTempo.setText("");
                    txtNota.setText("");
                } catch (NumberFormatException ex) {
                    MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR);
                    mb.setMessage("Insira valores numericos validos!");
                    mb.open();
                }
            }
        });

        btnAtualizar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                listConsulta.removeAll();
                
                for (TentativaSimulado t : bancoFakeTentativas) {
                    listConsulta.add("ID: " + t.getId() + " | Nota: " + t.getNotaObtida() + " | Tempo: " + t.getTempoGastoSegundos() + "s");
                }
                
                if(bancoFakeTentativas.isEmpty()) {
                    listConsulta.add("Nenhum tentativa registrada no sistema.");
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
