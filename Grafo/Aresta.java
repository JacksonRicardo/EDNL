package grafo;

// A classe Aresta representa uma conexão entre dois vértices em um grafo.
public class Aresta {
    private Vertice destino;  // O vértice para o qual esta aresta aponta.

    // Construtor para a classe Aresta.
    public Aresta(Vertice destino) {
        this.destino = destino;
    }

    // Retorna o vértice de destino da aresta.
    public Vertice getDestino() {
        return this.destino;
    }
}

