import java.util.ArrayList;
import java.util.List;


public abstract class Usuario {
    private String nome;
    private String email;
    private List<Playlist> playlists = new ArrayList<>();      // 1 usuário -> N playlists
    private List<Artista> artistasSeguidos = new ArrayList<>(); // N usuários <-> N artistas
    private Player player = new Player();

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // ---- regras que mudam conforme o plao ----

    public abstract String getPlano();

    public abstract int getLimitePlaylists();


    public abstract boolean autorizarPulo();

    // ---- compotamento comum ----


    public Playlist criarPlaylist(String nomePlaylist) {
        if (playlists.size() >= getLimitePlaylists()) {
            return null;
        }
        Playlist p = new Playlist(nomePlaylist);
        playlists.add(p);
        return p;
    }


    public boolean seguirArtista(Artista artista) {
        if (artistasSeguidos.contains(artista)) {
            return false;
        }
        artistasSeguidos.add(artista);
        return true;
    }


    public boolean pularFaixa() {
        if (!autorizarPulo()) {
            return false;
        }
        player.proxima();
        return true;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public List<Artista> getArtistasSeguidos() {
        return artistasSeguidos;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return nome + " (plano " + getPlano() + ")";
    }
}
