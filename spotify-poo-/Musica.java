public class Musica extends Midia {
    private Artista artista;
    private String album;
    private String genero;

    public Musica(String titulo, int duracaoSegundos, Artista artista, String album, String genero) {
        super(titulo, duracaoSegundos);
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        artista.adicionarMusica(this); // relação Artista 1 --- * Musica
    }

    public Artista getArtista() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String getResponsavel() {
        return artista.getNome();
    }

    @Override
    public String getTipo() {
        return "Música";
    }

    @Override
    public String descricao() {
        return String.format("Música: %s - %s | Álbum: %s | Gênero: %s (%s)",
                getTitulo(), artista.getNome(), album, genero, getDuracaoFormatada());
    }
}
