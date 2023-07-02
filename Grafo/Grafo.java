package grafo;

import java.util.*;

// A classe Grafo representa um conjunto de vértices e arestas.
public class Grafo {
    private List<Vertice> vertices;  // Uma lista de todos os vértices no grafo.

    // Construtor para a classe Grafo.
    public Grafo() {
        // Inicializamos a lista de vértices como uma lista vazia.
        this.vertices = new ArrayList<>();  
    }

    // Método para adicionar um vértice à lista de vértices do grafo.
    public void adicionarVertice(Vertice vertice) {
        // Adiciona o vértice passado como parâmetro à lista de vértices.
        this.vertices.add(vertice);
    }

    // Método para adicionar uma aresta ao grafo. A aresta começa no vértice 'origem' e aponta para o vértice 'destino'.
    public void adicionarAresta(Vertice origem, Vertice destino) {
        // Cria uma nova aresta que aponta para o vértice 'destino' e a adiciona à lista de arestas do vértice 'origem'.
        origem.adicionarAresta(new Aresta(destino));
    }

    // Método para encontrar um vértice pelo nome.
    public Vertice encontrarVertice(String nome) {
        for (Vertice v : this.vertices) {
            if (v.getNome().equals(nome)) {
                return v;
            }
        }
        return null;
    }

    // Método para remover um vértice.
    public void removerVertice(String nome) {
        Vertice v = encontrarVertice(nome);
        if (v != null) {
            this.vertices.remove(v);
        }
    }

    // Método para remover uma aresta.
    public void removerAresta(String nomeOrigem, String nomeDestino) {
        Vertice origem = encontrarVertice(nomeOrigem);
        Vertice destino = encontrarVertice(nomeDestino);
        if (origem != null && destino != null) {
            origem.getArestas().removeIf(a -> a.getDestino().equals(destino));
        }
    }

    // Método para imprimir uma representação textual do grafo.
public void imprimirGrafo() {
    // Percorre todos os vértices na lista de vértices.
    for (Vertice v : vertices) {
        // Cria um StringBuilder para construir a string a ser impressa.
        StringBuilder sb = new StringBuilder();
        // Adiciona o nome do vértice atual e a string " está conectado com: " ao StringBuilder.
        sb.append(v.getNome()).append(" está conectado com: ");
        // Percorre todas as arestas do vértice atual.
        for (Aresta a : v.getArestas()) {
            // Adiciona o nome do vértice destino da aresta atual, seguido de uma vírgula e um espaço, ao StringBuilder.
            sb.append(a.getDestino().getNome()).append(", ");
        }
        // Remove a última vírgula e o espaço em branco, se houverem.
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        // Imprime a string construída.
        System.out.println(sb.toString());
        }
    }
}