public abstract class Midia {
    private static int proximoId = 1;

    private final int id;
    private String titulo;
    private int duracaoSegundos;

    public Midia(String titulo, int duracaoSegundos) {
        this.id = proximoId++;
        this.titulo = titulo;
        this.duracaoSegundos = duracaoSegundos;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public String getDuracaoFormatada() {
        return String.format("%d:%02d", duracaoSegundos / 60, duracaoSegundos % 60);
    }


    public abstract String getResponsavel();


    public abstract String getTipo();


    public abstract String descricao();

    @Override
    public String toString() {
        return String.format("[%d] %s - %s (%s) <%s>",
                id, titulo, getResponsavel(), getDuracaoFormatada(), getTipo());
    }
}
