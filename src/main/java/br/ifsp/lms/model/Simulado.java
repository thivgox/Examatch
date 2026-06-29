package br.ifsp.lms.model;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Simulado {
    private int id;
    private String titulo;
    private Date dataCriacao;
    private List<Questao> questoes;
    private List<Tag> tags;

    public Simulado(int id, String titulo, Date dataCriacao) {
        this.id = id;
        this.titulo = titulo;
        this.dataCriacao = dataCriacao;
        this.questoes = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void adicionarQuestao(Questao questao) {
        this.questoes.add(questao);
    }

    public List<Questao> filtrarQuestoesPorTag(Tag tag) {
        List<Questao> questoesFiltradas = new ArrayList<>();
        for (Questao q : this.questoes) {
            if (q.getTags() != null && q.getTags().contains(tag)) {
                questoesFiltradas.add(q);
            }
        }
        return questoesFiltradas;
    }
}
