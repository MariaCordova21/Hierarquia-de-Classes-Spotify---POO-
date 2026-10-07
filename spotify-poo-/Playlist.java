import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String nome;
    private List<Midia> itens = new ArrayList<>();

    public Playlist(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Midia> getItens() {
        return itens;
    }

    public boolean estaVazia() {
        return itens.isEmpty();
    }


    public boolean adicionar(Midia midia) {
        if (itens.contains(midia)) {
            return false;
        }
        itens.add(midia);
        return true;
    }

    public boolean remover(int idMidia) {
        return itens.removeIf(m -> m.getId() == idMidia);
    }

    public int getDuracaoTotalSegundos() {
        int total = 0;
        for (Midia m : itens) {
            total += m.getDuracaoSegundos();
        }
        return total;
    }

    public String getDuracaoTotalFormatada() {
        int total = getDuracaoTotalSegundos();
        return String.format("%d:%02d", total / 60, total % 60);
    }

    public void exibir() {
        System.out.println("Playlist: " + nome + " - " + itens.size() + " item(ns), "
                + getDuracaoTotalFormatada());
        if (itens.isEmpty()) {
            System.out.println("   (vazia)");
        }
        for (Midia m : itens) {
            System.out.println("   " + m);
        }
    }
}
