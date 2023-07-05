package algoritmoa;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LabirintoAStar {
    private static final int CAMINHO_VAZIO = 0;
    private static final int PAREDE = 1;
    private static final int PONTO_PARTIDA = 2;
    private static final int SAIDA = 3;

    private int[][] labirinto;
    private int linhas;
    private int colunas;
    private Celula pontoPartida;
    private List<Celula> pontosSaida;
    private List<Celula> caminho;

    public LabirintoAStar(int[][] labirinto) {
        this.labirinto = labirinto;
        this.linhas = labirinto.length;
        this.colunas = labirinto[0].length;
        this.caminho = new ArrayList<>();
        this.pontoPartida = encontrarCelula(PONTO_PARTIDA);
        this.pontosSaida = encontrarCelulas(SAIDA);
    }

   public List<Celula> encontrarCaminho() {
    PriorityQueue<Celula> fronteira = new PriorityQueue<>();
    boolean[][] visitado = new boolean[linhas][colunas];

    fronteira.add(pontoPartida);

    while (!fronteira.isEmpty()) {
        Celula atual = fronteira.poll();

        // Verificar se a célula atual é uma das saídas
        for (Celula saida : pontosSaida) {
            if (atual.equals(saida)) {
                // Caminho encontrado, reconstruir o caminho percorrido
                Celula celula = atual;
                while (celula != null) {
                    caminho.add(0, celula);  // adiciona ao início da lista para manter a ordem correta
                    celula = celula.pai;
                }
                return caminho;
            }
        }

        List<Celula> vizinhos = obterVizinhos(atual);
        for (Celula vizinho : vizinhos) {
            if (visitado[vizinho.linha][vizinho.coluna]) {
                continue;
            }

            int gTemp = atual.g + 1; // Custo para mover para o vizinho é sempre 1

            if (!fronteira.contains(vizinho) || gTemp < vizinho.g) {
                vizinho.pai = atual;
                vizinho.g = gTemp;
                vizinho.h = calcularHeuristica(vizinho);
                vizinho.f = vizinho.g + vizinho.h;

                if (fronteira.contains(vizinho)) {
                    fronteira.remove(vizinho);
                }
                fronteira.add(vizinho);
            }
        }

        visitado[atual.linha][atual.coluna] = true;
    }

    return caminho;
}


    private Celula encontrarCelula(int valor) {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (labirinto[i][j] == valor) {
                    return new Celula(i, j);
                }
            }
        }
        return null;
    }

    private List<Celula> encontrarCelulas(int valor) {
        List<Celula> celulas = new ArrayList<>();
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (labirinto[i][j] == valor) {
                    celulas.add(new Celula(i, j));
                }
            }
        }
        return celulas;
    }

private List<Celula> obterVizinhos(Celula celula) {
    List<Celula> vizinhos = new ArrayList<>();

    int linha = celula.linha;
    int coluna = celula.coluna;

    if (linha > 0 && labirinto[linha - 1][coluna] != PAREDE) {
        Celula vizinho = new Celula(linha - 1, coluna);
        vizinho.pai = celula;
        vizinho.g = celula.g + 1;
        vizinho.h = calcularHeuristica(vizinho);
        vizinho.f = vizinho.g + vizinho.h;
        vizinhos.add(vizinho);
    }
    if (linha < linhas - 1 && labirinto[linha + 1][coluna] != PAREDE) {
        Celula vizinho = new Celula(linha + 1, coluna);
        vizinho.pai = celula;
        vizinho.g = celula.g + 1;
        vizinho.h = calcularHeuristica(vizinho);
        vizinho.f = vizinho.g + vizinho.h;
        vizinhos.add(vizinho);
    }
    if (coluna > 0 && labirinto[linha][coluna - 1] != PAREDE) {
        Celula vizinho = new Celula(linha, coluna - 1);
        vizinho.pai = celula;
        vizinho.g = celula.g + 1;
        vizinho.h = calcularHeuristica(vizinho);
        vizinho.f = vizinho.g + vizinho.h;
        vizinhos.add(vizinho);
    }
    if (coluna < colunas - 1 && labirinto[linha][coluna + 1] != PAREDE) {
        Celula vizinho = new Celula(linha, coluna + 1);
        vizinho.pai = celula;
        vizinho.g = celula.g + 1;
        vizinho.h = calcularHeuristica(vizinho);
        vizinho.f = vizinho.g + vizinho.h;
        vizinhos.add(vizinho);
    }

    return vizinhos;
}


    private int calcularHeuristica(Celula celula) {
        // Utilizando a distância de Manhattan como heurística
        int h = Integer.MAX_VALUE;
        for (Celula saida : pontosSaida) {
            int hTemp = Math.abs(celula.linha - saida.linha) + Math.abs(celula.coluna - saida.coluna);
            if (hTemp < h) {
                h = hTemp;
            }
        }
        return h;
    }
}
