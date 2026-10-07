public class Episodio extends Midia {
    private String programa;
    private String apresentador;
    private int numero;

    public Episodio(String titulo, int duracaoSegundos, String programa, String apresentador, int numero) {
        super(titulo, duracaoSegundos);
        this.programa = programa;
        this.apresentador = apresentador;
        this.numero = numero;
    }

    public String getPrograma() {
        return programa;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String getResponsavel() {
        return apresentador;
    }

    @Override
    public String getTipo() {
        return "Podcast";
    }

    @Override
    public String descricao() {
        return String.format("Podcast: %s #%d - %s | Apresentação: %s (%s)",
                programa, numero, getTitulo(), apresentador, getDuracaoFormatada());
    }
}
