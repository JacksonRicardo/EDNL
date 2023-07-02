
package grafo;

/**
 *
 * @author jacks
 */
import java.util.*;

// A classe Vertice representa um nó em um grafo.
public class Vertice {
    private String nome;  // O nome do vértice. Isso pode ser qualquer coisa que você quiser usar para identificar o vértice.
    private List<Aresta> arestas;  // Uma lista de todas as arestas que saem deste vértice.

    // Construtor para a classe Vertice.
    public Vertice(String nome) {
        this.nome = nome;
        this.arestas = new ArrayList<>();  // Inicializamos a lista de arestas como uma lista vazia.
    }

    // Retorna o nome do vértice.
    public String getNome() {
        return this.nome;
    }

    // Adiciona uma aresta à lista de arestas do vértice.
    public void adicionarAresta(Aresta aresta) {
        this.arestas.add(aresta);
    }

    // Retorna a lista de arestas do vértice.
    public List<Aresta> getArestas() {
        return this.arestas;
    }
}

