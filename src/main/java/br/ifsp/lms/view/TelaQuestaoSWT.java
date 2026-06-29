package br.ifsp.lms.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import br.ifsp.lms.model.Questao;
import br.ifsp.lms.model.Tag;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelaQuestaoSWT {

    public static List<Questao> bancoFakeQuestoes = new ArrayList<>();

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("LMS Exatas - Gestao de Questoes");
        shell.setSize(600, 600);
        shell.setLayout(new GridLayout(2, false));

        Label lblTituloCad = new Label(shell, SWT.NONE);
        lblTituloCad.setText("CADASTRAR QUESTAO");
        lblTituloCad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        new Label(shell, SWT.NONE).setText("ID:");
        Text txtId = new Text(shell, SWT.BORDER);
        txtId.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        new Label(shell, SWT.NONE).setText("Enunciado:");
        Text txtEnunciado = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        GridData gdEnunciado = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gdEnunciado.heightHint = 80;
        txtEnunciado.setLayoutData(gdEnunciado);

        new Label(shell, SWT.NONE).setText("Alternativas (separadas por ;):");
        Text txtAlternativas = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        GridData gdAlternativas = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gdAlternativas.heightHint = 60;
        txtAlternativas.setLayoutData(gdAlternativas);

        new Label(shell, SWT.NONE).setText("Gabarito Correto (A, B, C, D ou E):");
        Text txtGabarito = new Text(shell, SWT.BORDER);
        txtGabarito.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        new Label(shell, SWT.NONE).setText("Explicacao do Erro:");
        Text txtExplicacaoErro = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        GridData gdExplicacaoErro = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gdExplicacaoErro.heightHint = 60;
        txtExplicacaoErro.setLayoutData(gdExplicacaoErro);

        new Label(shell, SWT.NONE).setText("Tags (IDs separadas por vírgula):");
        Text txtTags = new Text(shell, SWT.BORDER);
        txtTags.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        Button btnSalvar = new Button(shell, SWT.PUSH);
        btnSalvar.setText("Salvar Questao");
        btnSalvar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        Label separator = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        Label lblTituloCons = new Label(shell, SWT.NONE);
        lblTituloCons.setText("QUESTOES CADASTRADAS");
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
                if(txtId.getText().isEmpty() || txtEnunciado.getText().isEmpty() || txtAlternativas.getText().isEmpty() || txtGabarito.getText().isEmpty()) {
                    MessageBox mb = new MessageBox(shell, SWT.ICON_WARNING);
                    mb.setMessage("Por favor, preencha os campos obrigatórios (ID, Enunciado, Alternativas, Gabarito Correto)!");
                    mb.open();
                    return;
                }

                try {
                    int id = Integer.parseInt(txtId.getText());
                    List<String> alternativas = Arrays.asList(txtAlternativas.getText().split(";"));
                    char gabarito = txtGabarito.getText().charAt(0);
                    String explicacao = txtExplicacaoErro.getText();

                    Questao novaQuestao = new Questao(id, txtEnunciado.getText(), alternativas, gabarito, explicacao);

                    if (!txtTags.getText().isEmpty()) {
                        List<Tag> tagsQuestao = new ArrayList<>();
                        String[] tagIds = txtTags.getText().split(",");
                        for (String tagIdStr : tagIds) {
                            try {
                                int tagId = Integer.parseInt(tagIdStr.trim());
                                // Para simplificar, estamos criando novas Tags aqui. Em um cenário real, você buscaria Tags existentes.
                                tagsQuestao.add(new Tag(tagId, "Tag " + tagId)); 
                            } catch (NumberFormatException ex) {
                                // Ignorar IDs de tag inválidos
                            }
                        }
                        novaQuestao.setTags(tagsQuestao);
                    }

                    bancoFakeQuestoes.add(novaQuestao);

                    MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION);
                    mb.setMessage("Questão cadastrada com sucesso!");
                    mb.open();

                    txtId.setText("");
                    txtEnunciado.setText("");
                    txtAlternativas.setText("");
                    txtGabarito.setText("");
                    txtExplicacaoErro.setText("");
                    txtTags.setText("");
                } catch (NumberFormatException ex) {
                    MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR);
                    mb.setMessage("ID deve ser um número inteiro válido.");
                    mb.open();
                } catch (StringIndexOutOfBoundsException ex) {
                    MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR);
                    mb.setMessage("Gabarito Correto deve ter pelo menos um caractere.");
                    mb.open();
                }
            }
        });

        btnAtualizar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                listConsulta.removeAll();
                
                for (Questao q : bancoFakeQuestoes) {
                    listConsulta.add("ID: " + q.getId() + " | " + q.getEnunciado().substring(0, Math.min(q.getEnunciado().length(), 50)) + "...");
                }
                
                if(bancoFakeQuestoes.isEmpty()) {
                    listConsulta.add("Nenhuma questão registrada no sistema.");
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
