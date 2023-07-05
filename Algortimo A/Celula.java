
package algoritmoa;

/**
 *
 * @author jacks
 */
public class Celula implements Comparable<Celula> {
    int linha;
    int coluna;
    int f;  // Custo total (f = g + h)
    int g;  // Custo do caminho percorrido até a célula atual
    int h;  // Heurística (distância estimada até a célula de saída)
    Celula pai;

    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    @Override
    public int compareTo(Celula outra) {
        return Integer.compare(this.f, outra.f);
    }
}

