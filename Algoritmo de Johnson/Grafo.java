package algorithmjohnson;
/**
 *
 * @author jacks
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Grafo {
    static class Vizinho {
        int destino;
        int peso;

        Vizinho(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    private int vertices;
    private final ArrayList<ArrayList<Vizinho>> listaAdjacencia;

    public Grafo(int vertices) {
        this.vertices = vertices;

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

    public void addAresta(int origem, int destino, int peso) {
        listaAdjacencia.get(origem).add(new Vizinho(destino, peso));
    }

    public int[] dijkstra(int origem) {
        boolean[] foiVisitado = new boolean[vertices];
        int[] distancia = new int[vertices];

        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[origem] = 0;

        for (int vertice = 0; vertice < vertices; vertice++) {
            int verticeMinDistancia = encontrarVerticeMinDistancia(distancia, foiVisitado);
            foiVisitado[verticeMinDistancia] = true;

            for (Vizinho vizinho : listaAdjacencia.get(verticeMinDistancia)) {
                int destino = vizinho.destino;
                int peso = vizinho.peso;

                if (!foiVisitado[destino] && distancia[verticeMinDistancia] + peso < distancia[destino])
                    distancia[destino] = distancia[verticeMinDistancia] + peso;
            }
        }

        return distancia;
    }

    private int encontrarVerticeMinDistancia(int[] distancia, boolean[] foiVisitado) {
        int indiceMin = -1, distanciaMin = Integer.MAX_VALUE;

        for (int vertice = 0; vertice < vertices; vertice++) {
            if (!foiVisitado[vertice] && distancia[vertice] <= distanciaMin) {
                distanciaMin = distancia[vertice];
                indiceMin = vertice;
            }
        }

        return indiceMin;
    }

    public int[] bellmanFord(int origem) {
        int[] distancia = new int[vertices];

        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[origem] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            for (int verticeAtual = 0; verticeAtual < vertices; verticeAtual++) {
                for (Vizinho vizinho : listaAdjacencia.get(verticeAtual)) {
                    if (distancia[verticeAtual] != Integer.MAX_VALUE && distancia[verticeAtual] + vizinho.peso < distancia[vizinho.destino]) {
                        distancia[vizinho.destino] = distancia[verticeAtual] + vizinho.peso;
                    }
                }
            }
        }

        for (int verticeAtual = 0; verticeAtual < vertices; verticeAtual++) {
            for (Vizinho vizinho : listaAdjacencia.get(verticeAtual)) {
                if (distancia[verticeAtual] != Integer.MAX_VALUE && distancia[verticeAtual] + vizinho.peso < distancia[vizinho.destino])
                    return null;
            }
        }

        return distancia;
    }

    public int[][] johnsons() {
        this.vertices++;
        listaAdjacencia.add(new ArrayList<>());
        for (int i = 0; i < vertices - 1; i++)
            listaAdjacencia.get(vertices - 1).add(new Vizinho(i, 0));

        int[] h = bellmanFord(vertices - 1);
        if (h == null)
            return null;

        for (int u = 0; u < vertices; u++) {
            ArrayList<Vizinho> vizinhos = listaAdjacencia.get(u);

            for (Vizinho vizinho : vizinhos) {
                int v = vizinho.destino;
                int w = vizinho.peso;

                vizinho.peso = w + h[u] - h[v];
            }
        }

        listaAdjacencia.remove(vertices - 1);
        vertices--;

        int[][] distancias = new int[vertices][];

        for (int s = 0; s < vertices; s++)
            distancias[s] = dijkstra(s);

        for (int u = 0; u < vertices; u++) {
            for (int v = 0; v < vertices; v++) {
                if (distancias[u][v] == Integer.MAX_VALUE)
                    continue;

                distancias[u][v] += (h[v] - h[u]);
            }
        }

        return distancias;
    }

    public static void main(String[] args) {
        final int vertices = 4;
        final int[][] matriz = {
            { 0, 0, -2, 0 },
            { 4, 0, 3, 0 },
            { 0, 0, 0, 2 },
            { 0, -1, 0, 0 }
        };

        Grafo grafo = new Grafo(vertices, matriz);

        int[][] distancias = grafo.johnsons();

        if (distancias == null) {
            System.out.println("Ciclo com peso negativo detectado.");
            return;
        }

        System.out.println("Matriz de distâncias:");

        System.out.print("  \t");
        for (int i = 0; i < vertices; i++)
            System.out.printf("%3d\t", i);

        for (int i = 0; i < vertices; i++) {
            System.out.println();
            System.out.printf("%3d\t", i);
            for (int j = 0; j < vertices; j++) {
                if (distancias[i][j] == Integer.MAX_VALUE)
                    System.out.print(" X\t");
                else
                    System.out.printf("%3d\t", distancias[i][j]);
            }
        }
    }
}


