import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner entrada = new Scanner(System.in);
    private static final Catalogo catalogo = new Catalogo();
    private static Usuario usuario;

    public static void main(String[] args) {
        System.out.println("=== SPOTIFY (simulação) ===");
        usuario = criarUsuario();

        int opcao;
        do {
            exibirMenu();
            opcao = lerInt("Escolha uma opção: ");
            executar(opcao);
        } while (opcao != 0);

        System.out.println("Até logo, " + usuario.getNome() + "!");
    }

    //------- menu ---------

    private static void exibirMenu() {
        System.out.println();
        System.out.println("---- MENU (" + usuario + ") ----");
        System.out.println("1  - Ver catálogo");
        System.out.println("2  - Buscar música/podcast");
        System.out.println("3  - Criar playlist");
        System.out.println("4  - Adicionar item a uma playlist");
        System.out.println("5  - Remover item de uma playlist");
        System.out.println("6  - Ver minhas playlists");
        System.out.println("7  - Tocar (playlist ou item)");
        System.out.println("8  - Pular faixa");
        System.out.println("9  - Seguir artista");
        System.out.println("10 - Ver artistas que sigo");
        System.out.println("0  - Sair");
    }

    private static void executar(int opcao) {
        switch (opcao) {
            case 1: verCatalogo(); break;
            case 2: buscar(); break;
            case 3: criarPlaylist(); break;
            case 4: adicionarNaPlaylist(); break;
            case 5: removerDaPlaylist(); break;
            case 6: verPlaylists(); break;
            case 7: tocar(); break;
            case 8: pular(); break;
            case 9: seguirArtista(); break;
            case 10: verSeguidos(); break;
            case 0: break;
            default: System.out.println("Opção inválida.");
        }
    }

    //-------- ações --------

    private static Usuario criarUsuario() {
        String nome = lerTexto("Seu nome: ");
        if (nome.isEmpty()) {
            nome = "Usuário";
        }
        String email = lerTexto("Seu e-mail: ");
        int plano = 0;
        while (plano != 1 && plano != 2) {
            plano = lerInt("Plano (1 - Free, 2 - Premium): ");
            if (plano == 0 && !entrada.hasNextLine()) {
                plano = 1; // sem mais entrada: assume Free
            }
        }
        return plano == 2 ? new UsuarioPremium(nome, email) : new UsuarioFree(nome, email);
    }

    private static void verCatalogo() {
        System.out.println("--- Músicas e podcasts ---");
        for (Midia m : catalogo.getMidias()) {
            System.out.println(m);
        }
    }

    private static void buscar() {
        String termo = lerTexto("Buscar por (título/artista): ");
        List<Midia> resultado = catalogo.buscar(termo);
        if (resultado.isEmpty()) {
            System.out.println("Nada encontrado para \"" + termo + "\".");
            return;
        }
        System.out.println(resultado.size() + " resultado(s):");
        for (Midia m : resultado) {
            System.out.println(m);
        }
    }

    private static void criarPlaylist() {
        String nome = lerTexto("Nome da playlist: ");
        if (nome.isEmpty()) {
            System.out.println("O nome não pode ser vazio.");
            return;
        }
        Playlist p = usuario.criarPlaylist(nome);
        if (p == null) {
            System.out.println("Limite de playlists do plano " + usuario.getPlano()
                    + " atingido (" + usuario.getLimitePlaylists() + "). Assine o Premium!");
        } else {
            System.out.println("Playlist \"" + p.getNome() + "\" criada.");
        }
    }

    private static void adicionarNaPlaylist() {
        Playlist p = escolherPlaylist();
        if (p == null) {
            return;
        }
        int id = lerInt("Id da música/podcast (veja no catálogo): ");
        Midia m = catalogo.buscarPorId(id);
        if (m == null) {
            System.out.println("Id não encontrado.");
        } else if (p.adicionar(m)) {
            System.out.println("\"" + m.getTitulo() + "\" adicionada a \"" + p.getNome() + "\".");
        } else {
            System.out.println("Esse item já está na playlist.");
        }
    }

    private static void removerDaPlaylist() {
        Playlist p = escolherPlaylist();
        if (p == null) {
            return;
        }
        p.exibir();
        int id = lerInt("Id do item a remover: ");
        if (p.remover(id)) {
            System.out.println("Item removido.");
        } else {
            System.out.println("Esse id não está na playlist.");
        }
    }

    private static void verPlaylists() {
        if (usuario.getPlaylists().isEmpty()) {
            System.out.println("Você ainda não tem playlists.");
            return;
        }
        for (Playlist p : usuario.getPlaylists()) {
            p.exibir();
        }
    }

    private static void tocar() {
        int tipo = lerInt("Tocar: 1 - playlist, 2 - item do catálogo: ");
        if (tipo == 1) {
            Playlist p = escolherPlaylist();
            if (p != null && !usuario.getPlayer().tocarPlaylist(p)) {
                System.out.println("A playlist está vazia.");
            }
        } else if (tipo == 2) {
            int id = lerInt("Id do item: ");
            Midia m = catalogo.buscarPorId(id);
            if (m == null) {
                System.out.println("Id não encontrado.");
            } else {
                usuario.getPlayer().tocarMidia(m);
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void pular() {
        if (usuario.getPlayer().getAtual() == null) {
            System.out.println("Nada está tocando.");
        } else if (!usuario.getPlayer().temProxima()) {
            System.out.println("Não há próxima faixa na fila.");
        } else if (!usuario.pularFaixa()) {
            System.out.println("Seus pulos do plano Free acabaram. Assine o Premium!");
        } else if (usuario instanceof UsuarioFree) {
            System.out.println("Pulos restantes: " + ((UsuarioFree) usuario).getPulosRestantes());
        }
    }

    private static void seguirArtista() {
        List<Artista> artistas = catalogo.getArtistas();
        for (int i = 0; i < artistas.size(); i++) {
            System.out.println((i + 1) + ") " + artistas.get(i));
        }
        int n = lerInt("Número do artista: ");
        if (n < 1 || n > artistas.size()) {
            System.out.println("Artista inválido.");
            return;
        }
        Artista a = artistas.get(n - 1);
        if (usuario.seguirArtista(a)) {
            System.out.println("Agora você segue " + a.getNome() + ".");
        } else {
            System.out.println("Você já segue " + a.getNome() + ".");
        }
    }

    private static void verSeguidos() {
        if (usuario.getArtistasSeguidos().isEmpty()) {
            System.out.println("Você ainda não segue nenhum artista.");
            return;
        }
        for (Artista a : usuario.getArtistasSeguidos()) {
            System.out.println("- " + a);
        }
    }

    //------ utilitários de entrada -----

    private static Playlist escolherPlaylist() {
        List<Playlist> lista = usuario.getPlaylists();
        if (lista.isEmpty()) {
            System.out.println("Você ainda não tem playlists. Crie uma na opção 3.");
            return null;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ") " + lista.get(i).getNome()
                    + " (" + lista.get(i).getItens().size() + " itens)");
        }
        int n = lerInt("Número da playlist: ");
        if (n < 1 || n > lista.size()) {
            System.out.println("Playlist inválida.");
            return null;
        }
        return lista.get(n - 1);
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.hasNextLine() ? entrada.nextLine().trim() : "";
    }

    /** Lê um inteiro; se a entrada acabar ou for inválida, devolve 0 (sair/inválido). */
    private static int lerInt(String mensagem) {
        String texto = lerTexto(mensagem);
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
