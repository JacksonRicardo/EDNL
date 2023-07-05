
package algoritmoa;
import java.util.List;


/**
 *
 * @author jacks
 */

public class Main {
    public static void main(String[] args) {
        int[][] labirinto = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 2, 0, 0, 1, 0, 1, 0, 1, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 3, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                {3, 1, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 1, 0, 1, 0, 0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 3, 0, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        LabirintoAStar labirintoAStar = new LabirintoAStar(labirinto);
        List<Celula> caminho = labirintoAStar.encontrarCaminho();

        if (caminho.isEmpty()) {
            System.out.println("Não foi possível encontrar um caminho até a saída.");
        } else {
            System.out.println("Caminho percorrido:");
            for (Celula celula : caminho) {
                System.out.println("(" + celula.linha + ", " + celula.coluna + ")");
            }
        }
    }
}


