import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Midia> fila = new ArrayList<>();
    private int indice = -1;

    public void tocarMidia(Midia midia) {
        fila.clear();
        fila.add(midia);
        indice = 0;
        exibirAtual();
    }


    public boolean tocarPlaylist(Playlist playlist) {
        if (playlist.estaVazia()) {
            return false;
        }
        fila.clear();
        fila.addAll(playlist.getItens());
        indice = 0;
        exibirAtual();
        return true;
    }

    public boolean temProxima() {
        return indice >= 0 && indice + 1 < fila.size();
    }

    public void proxima() {
        if (temProxima()) {
            indice++;
            exibirAtual();
        }
    }

    public Midia getAtual() {
        return indice >= 0 ? fila.get(indice) : null;
    }

    public void exibirAtual() {
        Midia atual = getAtual();
        if (atual == null) {
            System.out.println("Nada está tocando no momento.");
            return;
        }
        System.out.println("Tocando agora (" + (indice + 1) + "/" + fila.size() + "): "
                + atual.descricao());
    }
}
