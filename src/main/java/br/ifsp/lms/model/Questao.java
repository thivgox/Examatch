import java.util.List;

public class Questao {
    private int id;
    private String enunciado;
    private List<String> alternativas;
    private char gabaritoCorreto;
    private String explicacaoErro;
    private List<Tag> tags;

    public Questao(int id, String enunciado, List<String> alternativas, char gabaritoCorreto, String explicacaoErro) {
        this.id = id;
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.gabaritoCorreto = gabaritoCorreto;
        this.explicacaoErro = explicacaoErro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<String> alternativas) {
        this.alternativas = alternativas;
    }

    public char getGabaritoCorreto() {
        return gabaritoCorreto;
    }

    public void setGabaritoCorreto(char gabaritoCorreto) {
        this.gabaritoCorreto = gabaritoCorreto;
    }

    public String getExplicacaoErro() {
        return explicacaoErro;
    }

    public void setExplicacaoErro(String explicacaoErro) {
        this.explicacaoErro = explicacaoErro;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void vincularTag(Tag tag) {
        // Implementação para vincular uma tag
    }

    public boolean validarObrigatoriedadeTag() {
        // Implementação para validar obrigatoriedade de tag
        return false;
    }
}
