public class UsuarioPremium extends Usuario {

    public UsuarioPremium(String nome, String email) {
        super(nome, email);
    }

    @Override
    public String getPlano() {
        return "Premium";
    }

    @Override
    public int getLimitePlaylists() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean autorizarPulo() {
        return true;
    }
}
