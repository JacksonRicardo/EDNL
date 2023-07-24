package algorithmjohnson;
/**
 *
 * @author jacks
 */
import java.util.ArrayList;
import java.util.Arrays;

// Classe Grafo que representa um grafo com peso nas arestas, contendo várias utilidades para manipulação de grafos
public class Grafo {
    // Classe interna Vizinho que representa um vizinho no grafo (um vértice conectado por uma aresta)
    static class Vizinho {
        int destino; // O vértice de destino ao qual este vizinho aponta
        int peso;  // O peso da aresta para este vértice de destino

        // Construtor da classe Vizinho que inicializa o destino e o peso
        Vizinho(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    // Número total de vértices no grafo
    private int vertices;
    // Uma lista de adjacências para representar o grafo. Cada entrada da lista é outra lista contendo
    // todos os vizinhos do vértice correspondente à entrada.
    private final ArrayList<ArrayList<Vizinho>> listaAdjacencia;

    // Construtor principal do grafo que inicializa a lista de adjacências
    public Grafo(int vertices) {
        this.vertices = vertices;
        
        // Inicializando uma lista vazia para cada vértice
        listaAdjacencia = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++)
            listaAdjacencia.add(new ArrayList<>());
    }

    public Grafo(int vertices, int[][] matrizAdjacencia) {
        this(vertices);

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (matrizAdjacencia[i][j] != 0)
                    addAresta(i, j, matrizAdjacencia[i][j]);
            }
        }
    }
    
    // Método para adicionar uma aresta ao grafo
    // A aresta é adicionada à lista de vizinhos do vértice de origem
    public void addAresta(int origem, int destino, int peso) {
        listaAdjacencia.get(origem).add(new Vizinho(destino, peso));
    }
    
    // Algoritmo de Dijkstra para encontrar o caminho mais curto de um único vértice de origem
    public int[] dijkstra(int origem) {
        // O array foiVisitado mantém o controle de quais vértices foram visitados ao executar o algoritmo
        boolean[] foiVisitado = new boolean[vertices];
        // O array distancia mantém a menor distância encontrada de 'origem' para cada vértice
        int[] distancia = new int[vertices];
        // Inicializa todas as distâncias como infinito e a distância da origem para si mesma como 0
        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[origem] = 0;
        
        // Loop que executa uma vez para cada vértice
        for (int vertice = 0; vertice < vertices; vertice++) {
            // Encontra o vértice não visitado com a menor distância conhecida da origem
            int verticeMinDistancia = encontrarVerticeMinDistancia(distancia, foiVisitado);
            foiVisitado[verticeMinDistancia] = true;

             // Atualiza as distâncias conhecidas para todos os vizinhos do vérticeMinDistancia
            for (Vizinho vizinho : listaAdjacencia.get(verticeMinDistancia)) {
                int destino = vizinho.destino;
                int peso = vizinho.peso;

                // Se a distância através do verticeMinDistancia for menor que a atualmente conhecida, atualiza a distância
                if (!foiVisitado[destino] && distancia[verticeMinDistancia] + peso < distancia[destino])
                    distancia[destino] = distancia[verticeMinDistancia] + peso;
            }
        }
        // Retorna o array de distâncias
        return distancia;
    }

    // Método auxiliar para o Dijkstra que encontra o vértice com a menor distância conhecida que ainda não foi visitado
    private int encontrarVerticeMinDistancia(int[] distancia, boolean[] foiVisitado) {
        int indiceMin = -1, distanciaMin = Integer.MAX_VALUE;

        // Itera sobre todos os vértices
        for (int vertice = 0; vertice < vertices; vertice++) {
            // Se o vértice não foi visitado e a distância conhecida para ele é a menor encontrada até agora, atualiza a menor distância e o índice
            if (!foiVisitado[vertice] && distancia[vertice] <= distanciaMin) {
                distanciaMin = distancia[vertice];
                indiceMin = vertice;
            }
        }

        return indiceMin;
    }

    // Algoritmo de Bellman-Ford para encontrar o caminho mais curto de um único vértice de origem
    public int[] bellmanFord(int origem) {
        // O array distancia mantém a menor distância encontrada de 'origem' para cada vértice
        int[] distancia = new int[vertices];

        // Inicializa todas as distâncias como infinito e a distância da origem para si mesma como 0
        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[origem] = 0;

        // O algoritmo percorre todas as arestas 'vertices - 1' vezes
        for (int i = 0; i < vertices - 1; i++) {
            // Para cada aresta...
            for (int verticeAtual = 0; verticeAtual < vertices; verticeAtual++) {
                for (Vizinho vizinho : listaAdjacencia.get(verticeAtual)) {
                    // ...se a distância através da aresta for menor que a atualmente conhecida, atualiza a distância
                    if (distancia[verticeAtual] != Integer.MAX_VALUE && distancia[verticeAtual] + vizinho.peso < distancia[vizinho.destino]) {
                        distancia[vizinho.destino] = distancia[verticeAtual] + vizinho.peso;
                    }
                }
            }
        }

        // Após 'vertices - 1' iterações, se uma distância puder ser reduzida, então existe um ciclo de peso negativo
        for (int verticeAtual = 0; verticeAtual < vertices; verticeAtual++) {
            for (Vizinho vizinho : listaAdjacencia.get(verticeAtual)) {
                if (distancia[verticeAtual] != Integer.MAX_VALUE && distancia[verticeAtual] + vizinho.peso < distancia[vizinho.destino])
                    return null; // Se um ciclo de peso negativo for detectado, retorna null
            }
        }

        // Retorna o array de distâncias
        return distancia;
    }

    // Algoritmo de Johnson para encontrar a distância mais curta entre todos os pares de vértices
    public int[][] johnsons() {
        // Adiciona um novo vértice ao grafo que é conectado a todos os outros vértices
        this.vertices++;
        listaAdjacencia.add(new ArrayList<>());
        for (int i = 0; i < vertices - 1; i++)
            listaAdjacencia.get(vertices - 1).add(new Vizinho(i, 0));

        // Usa o algoritmo de Bellman-Ford para calcular as distâncias mais curtas do novo vértice para todos os outros
        int[] h = bellmanFord(vertices - 1);
        if (h == null)
            return null; // Se um ciclo de peso negativo foi encontrado, retorna null

        // Repondera todas as arestas do grafo usando as distâncias calculadas acima
        for (int u = 0; u < vertices; u++) {
            ArrayList<Vizinho> vizinhos = listaAdjacencia.get(u);

            for (Vizinho vizinho : vizinhos) {
                int v = vizinho.destino;
                int w = vizinho.peso;

                vizinho.peso = w + h[u] - h[v];
            }
        }

        // Remove o vértice adicional
        listaAdjacencia.remove(vertices - 1);
        vertices--;

        // Usa o algoritmo de Dijkstra para calcular as distâncias mais curtas entre todos os pares de vértices no grafo reponderado
        int[][] distancias = new int[vertices][];

        // Desfaz a reponderação das distâncias
        for (int s = 0; s < vertices; s++)
            distancias[s] = dijkstra(s);

        for (int u = 0; u < vertices; u++) {
            for (int v = 0; v < vertices; v++) {
                if (distancias[u][v] == Integer.MAX_VALUE)
                    continue;

                distancias[u][v] += (h[v] - h[u]);
            }
        }

        // Retorna a matriz de distâncias mais curtas entre todos os pares de vértices
        return distancias;
    }

    public static void main(String[] args) {
        // Cria um grafo com 4 vértices e as arestas e pesos fornecidos
        final int vertices = 4;
        final int[][] matriz = {
            { 0, 0, -2, 0 },
            { 4, 0, 3, 0 },
            { 0, 0, 0, 2 },
            { 0, -1, 0, 0 }
        };

        Grafo grafo = new Grafo(vertices, matriz);

        // Executa o algoritmo de Johnson no grafo para obter as distâncias mais curtas entre todos os pares de vértices
        int[][] distancias = grafo.johnsons();

        // Se um ciclo de peso negativo foi detectado, imprime uma mensagem e termina
        if (distancias == null) {
            System.out.println("Ciclo com peso negativo detectado.");
            return;
        }

        // Imprime a matriz de distâncias mais curtas
        System.out.println("Matriz de distâncias:");

        // Imprime dois espaços e um caractere de tabulação sem quebrar a linha
        System.out.print("  \t");
        
        // Imprime os índices dos vértices, cada um seguido por uma tabulação, na mesma linha
        for (int i = 0; i < vertices; i++)
            System.out.printf("%3d\t", i);

        // Loop para percorrer a matriz de distâncias
        for (int i = 0; i < vertices; i++) {
            // Quebra a linha para começar a imprimir a próxima linha na linha seguinte
            System.out.println();
            // Imprime o índice da linha da matriz (que corresponde ao vértice de origem) seguido por uma tabulação
            System.out.printf("%3d\t", i);
            
            // Loop para percorrer cada vértice
            for (int j = 0; j < vertices; j++) {
                // Verifica se a distância de i para j é infinita (ou seja, não há caminho de i para j)
                if (distancias[i][j] == Integer.MAX_VALUE)
                    // Imprime 'X' para indicar que a distância é infinita
                    System.out.print(" X\t");
                else
                    // Imprime a distância de i para j
                    System.out.printf("%3d\t", distancias[i][j]);
            }
        }
    }
}


