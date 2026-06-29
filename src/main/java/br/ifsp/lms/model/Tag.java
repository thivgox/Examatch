public class Tag {
    private int id;
    private String nomeConteudo;

    public Tag(int id, String nomeConteudo) {
        this.id = id;
        this.nomeConteudo = nomeConteudo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeConteudo() {
        return nomeConteudo;
    }

    public void setNomeConteudo(String nomeConteudo) {
        this.nomeConteudo = nomeConteudo;
    }
}
