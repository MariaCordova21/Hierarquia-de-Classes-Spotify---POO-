import java.util.ArrayList;
import java.util.List;

public class Artista {
    private String nome;
    private List<Musica> musicas = new ArrayList<>();

    public Artista(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
    }

    @Override
    public String toString() {
        return nome + " (" + musicas.size() + " músicas)";
    }
}
