# Diagrama de classes - Spotify (perspectiva do usuário)

Cole o bloco abaixo em https://mermaid.live (ou veja direto no GitHub) para gerar a imagem.

```mermaid
classDiagram
    direction LR

    class Midia {
        <<abstract>>
        -int id
        -String titulo
        -int duracaoSegundos
        +getDuracaoFormatada() String
        +getResponsavel()* String
        +getTipo()* String
        +descricao()* String
    }
    class Musica {
        -String album
        -String genero
    }
    class Episodio {
        -String programa
        -String apresentador
        -int numero
    }
    class Artista {
        -String nome
        +adicionarMusica(Musica)
    }
    class Playlist {
        -String nome
        +adicionar(Midia) boolean
        +remover(int) boolean
        +getDuracaoTotalFormatada() String
        +exibir()
    }
    class Player {
        -int indice
        +tocarMidia(Midia)
        +tocarPlaylist(Playlist) boolean
        +proxima()
        +temProxima() boolean
    }
    class Usuario {
        <<abstract>>
        -String nome
        -String email
        +criarPlaylist(String) Playlist
        +seguirArtista(Artista) boolean
        +pularFaixa() boolean
        +getPlano()* String
        +getLimitePlaylists()* int
        +autorizarPulo()* boolean
    }
    class UsuarioFree {
        -int pulosRestantes
    }
    class UsuarioPremium
    class Catalogo {
        +buscar(String) List~Midia~
        +buscarPorId(int) Midia
    }

    Midia <|-- Musica
    Midia <|-- Episodio
    Usuario <|-- UsuarioFree
    Usuario <|-- UsuarioPremium

    Artista "1" --> "*" Musica : possui
    Usuario "1" *-- "0..*" Playlist : cria
    Playlist "*" o-- "*" Midia : contém
    Usuario "*" --> "*" Artista : segue
    Usuario "1" *-- "1" Player : usa
    Player "1" --> "*" Midia : fila
    Catalogo "1" o-- "*" Midia
    Catalogo "1" o-- "*" Artista
```

## Relações

| Relação | Tipo | Cardinalidade |
|---|---|---|
| Musica e Episodio herdam de Midia | Herança | - |
| UsuarioFree e UsuarioPremium herdam de Usuario | Herança | - |
| Artista - Musica | Associação | 1 para N |
| Usuario - Playlist | Composição | 1 para N |
| Playlist - Midia | Agregação | N para N |
| Usuario - Artista (segue) | Associação | N para N |
| Usuario - Player | Composição | 1 para 1 |
