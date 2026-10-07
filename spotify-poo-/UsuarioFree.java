public class UsuarioFree extends Usuario {
    private static final int LIMITE_PULOS = 3;
    private static final int LIMITE_PLAYLISTS = 3;

    private int pulosRestantes = LIMITE_PULOS;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
    }

    public int getPulosRestantes() {
        return pulosRestantes;
    }

    @Override
    public String getPlano() {
        return "Free";
    }

    @Override
    public int getLimitePlaylists() {
        return LIMITE_PLAYLISTS;
    }

    @Override
    public boolean autorizarPulo() {
        if (pulosRestantes > 0) {
            pulosRestantes--;
            return true;
        }
        return false;
    }
}
