package br.ifsp.lms.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import br.ifsp.lms.model.MaterialEstudo;
import java.util.ArrayList;
import java.util.List;

public class TelaMaterialEstudoSWT {

    public static List<MaterialEstudo> bancoFakeMateriais = new ArrayList<>();

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("LMS Exatas - Gestao de Materiais de Estudo");
        shell.setSize(500, 450);
        shell.setLayout(new GridLayout(2, false));

        Label lblTituloCad = new Label(shell, SWT.NONE);
        lblTituloCad.setText("CADASTRAR MATERIAL DE ESTUDO");
        lblTituloCad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        new Label(shell, SWT.NONE).setText("Titulo:");
        Text txtTitulo = new Text(shell, SWT.BORDER);
        txtTitulo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        new Label(shell, SWT.NONE).setText("Conteudo:");
        Text txtConteudo = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        GridData gdConteudo = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gdConteudo.heightHint = 60;
        txtConteudo.setLayoutData(gdConteudo);

        Button btnSalvar = new Button(shell, SWT.PUSH);
        btnSalvar.setText("Salvar Material");
        btnSalvar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        Label separator = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        Label lblTituloCons = new Label(shell, SWT.NONE);
        lblTituloCons.setText("MATERIAIS CADASTRADOS");
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
                if(txtTitulo.getText().isEmpty() || txtConteudo.getText().isEmpty()) {
                    MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING);
                    mb.setMessage("Por favor, preencha todos os campos!");
                    mb.open();
                    return;
                }

                MaterialEstudo novoMaterial = new MaterialEstudo();
                novoMaterial.setId(bancoFakeMateriais.size() + 1);
                novoMaterial.setTitulo(txtTitulo.getText());
                novoMaterial.setConteudo(txtConteudo.getText());

                bancoFakeMateriais.add(novoMaterial);

                MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION);
                mb.setMessage("Material cadastrado com sucesso!");
                mb.open();

                txtTitulo.setText("");
                txtConteudo.setText("");
            }
        });

        btnAtualizar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                listConsulta.removeAll();
                
                for (MaterialEstudo m : bancoFakeMateriais) {
                    listConsulta.add("ID: " + m.getId() + " | " + m.getTitulo());
                }
                
                if(bancoFakeMateriais.isEmpty()) {
                    listConsulta.add("Nenhum material registrado no sistema.");
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
