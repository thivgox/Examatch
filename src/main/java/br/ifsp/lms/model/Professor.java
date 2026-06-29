package br.ifsp.lms.model;

import br.ifsp.lms.Usuario;

public class Professor extends Usuario {
    
    private String departamento;

    public Professor() {
        super();
    }

    public Professor(int id, String nome, String email, String senhaHash, String departamento) {
        super(id, nome, email, senhaHash);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void visualizarRelatorioTurma() {
        System.out.println("Carregando relatorio da turma");
    }
}
