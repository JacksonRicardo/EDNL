package grafo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Cria um novo grafo vazio.
        Grafo grafo = new Grafo();

        // Cria alguns vértices.
        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");
        Vertice v5 = new Vertice("v5");

        // Adiciona os vértices ao grafo.
        grafo.adicionarVertice(v1);
        grafo.adicionarVertice(v2);
        grafo.adicionarVertice(v3);
        grafo.adicionarVertice(v4);
        grafo.adicionarVertice(v5);

        // Adiciona algumas arestas ao grafo.
        grafo.adicionarAresta(v1, v2);
        grafo.adicionarAresta(v1, v2);  // Isso cria um multigrafo.
        grafo.adicionarAresta(v2, v3);
        grafo.adicionarAresta(v2, v3);  // Isso cria um multigrafo.
        grafo.adicionarAresta(v3, v4);
        grafo.adicionarAresta(v4, v5);
        grafo.adicionarAresta(v5, v1);

        // Imprime o grafo.
        grafo.imprimirGrafo();

        // Cria um Scanner para ler a entrada do usuário.
        Scanner scanner = new Scanner(System.in);

        // Pergunta ao usuário se ele quer remover um vértice ou uma aresta.
        System.out.println("Você quer remover um vértice ou uma aresta? (v/a)");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("v")) {
            // Se o usuário quer remover um vértice, pergunta qual vértice ele quer remover.
            System.out.println("Digite o nome do vértice que você quer remover:");
            String nomeVertice = scanner.nextLine();
            grafo.removerVertice(nomeVertice);
        } else if (resposta.equalsIgnoreCase("a")) {
            // Se o usuário quer remover uma aresta, pergunta quais são os vértices da aresta que ele quer remover.
            System.out.println("Digite o nome do vértice de origem da aresta que você quer remover:");
            String nomeOrigem = scanner.nextLine();
            System.out.println("Digite o nome do vértice de destino da aresta que você quer remover:");
            String nomeDestino = scanner.nextLine();
            grafo.removerAresta(nomeOrigem, nomeDestino);
        } else {
            System.out.println("Resposta inválida. Por favor, digite 'v' para remover um vértice ou 'a' para remover uma aresta.");
        }

        // Imprime o grafo atualizado.
        grafo.imprimirGrafo();

        // Fecha o scanner.
        scanner.close();
    }
}
