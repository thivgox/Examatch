package br.ifsp.lms.model;

import java.util.ArrayList;
import java.util.List;
import br.ifsp.lms.Tag;

public class MaterialEstudo {
    
    private int id;
    private String titulo;
    private String conteudo;
    private Professor professor;
    private List<Tag> tags;

    public MaterialEstudo() {
        this.tags = new ArrayList<>();
    }

    public MaterialEstudo(int id, String titulo, String conteudo, Professor professor) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.professor = professor;
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

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void adicionarTag(Tag tag) {
        this.tags.add(tag);
    }
}
