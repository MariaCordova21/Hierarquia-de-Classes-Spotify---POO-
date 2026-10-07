import java.util.ArrayList;
import java.util.List;


public class Catalogo {
    private List<Artista> artistas = new ArrayList<>();
    private List<Midia> midias = new ArrayList<>();

    public Catalogo() {
        Artista queen = novoArtista("Queen");
        novaMusica("Bohemian Rhapsody", 354, queen, "A Night at the Opera", "Rock");
        novaMusica("Don't Stop Me Now", 209, queen, "Jazz", "Rock");

        Artista daftPunk = novoArtista("Daft Punk");
        novaMusica("One More Time", 320, daftPunk, "Discovery", "Eletrônica");
        novaMusica("Get Lucky", 369, daftPunk, "Random Access Memories", "Eletrônica");

        Artista anitta = novoArtista("Anitta");
        novaMusica("Envolver", 193, anitta, "Versions of Me", "Pop");
        novaMusica("Girl from Rio", 197, anitta, "Versions of Me", "Pop");

        Artista legiao = novoArtista("Legião Urbana");
        novaMusica("Tempo Perdido", 301, legiao, "Dois", "Rock Nacional");
        novaMusica("Eduardo e Mônica", 271, legiao, "Dois", "Rock Nacional");

        midias.add(new Episodio("O que é programação orientada a objetos?", 1800,
                "Papo de Dev", "Ana Lima", 1));
        midias.add(new Episodio("Carreira em TI para iniciantes", 2400,
                "Papo de Dev", "Ana Lima", 2));
    }

    private Artista novoArtista(String nome) {
        Artista a = new Artista(nome);
        artistas.add(a);
        return a;
    }

    private void novaMusica(String titulo, int duracao, Artista artista, String album, String genero) {
        midias.add(new Musica(titulo, duracao, artista, album, genero));
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public List<Midia> getMidias() {
        return midias;
    }

    public List<Midia> buscar(String termo) {
        String t = termo.toLowerCase();
        List<Midia> resultado = new ArrayList<>();
        for (Midia m : midias) {
            if (m.getTitulo().toLowerCase().contains(t)
                    || m.getResponsavel().toLowerCase().contains(t)) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    public Midia buscarPorId(int id) {
        for (Midia m : midias) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }
}
