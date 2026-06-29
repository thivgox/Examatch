package br.ifsp.lms.model;

import java.util.Date;
import br.ifsp.lms.Aluno;
import br.ifsp.lms.Simulado;

public class TentativaSimulado {
    
    private int id;
    private int tempoGastoSegundos;
    private double notaObtida;
    private Date dataRealizacao;
    private Aluno aluno;
    private Simulado simulado;

    public TentativaSimulado() {
        this.dataRealizacao = new Date();
    }

    public TentativaSimulado(int id, int tempoGastoSegundos, double notaObtida, Date dataRealizacao, Aluno aluno, Simulado simulado) {
        this.id = id;
        this.tempoGastoSegundos = tempoGastoSegundos;
        this.notaObtida = notaObtida;
        this.dataRealizacao = dataRealizacao;
        this.aluno = aluno;
        this.simulado = simulado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTempoGastoSegundos() {
        return tempoGastoSegundos;
    }

    public void setTempoGastoSegundos(int tempoGastoSegundos) {
        this.tempoGastoSegundos = tempoGastoSegundos;
    }

    public double getNotaObtida() {
        return notaObtida;
    }

    public void setNotaObtida(double notaObtida) {
        this.notaObtida = notaObtida;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Simulado getSimulado() {
        return simulado;
    }

    public void setSimulado(Simulado simulado) {
        this.simulado = simulado;
    }
}
