package arvoreAVL;

public class MainAVL {

	public static void main(String[] args) {

		ArvoreAVL avl = new ArvoreAVL();
				
		avl.insert(null, 2);
		avl.insert(avl.root(), 5);
		avl.insert(avl.root(), 8);
		avl.insert(avl.root(), 10);
		avl.insert(avl.root(), 15);
		avl.insert(avl.root(), 22);
                System.out.println(avl.mostraArvore());
                System.out.println("--------------------------------------------");
                avl.insert(avl.root(), 25);
                System.out.println(avl.mostraArvore());
                System.out.println("--------------------------------------------");

		//avl.remove(avl.root());
		avl.remove(avl.search(5, avl.root()));
                System.out.println(avl.mostraArvore());
                //System.out.println("-----------------------");
		//System.out.println(avl.search(6, avl.root()).getPai().getElemento());
//		avl.rotacaoSimplesEsquerda(avl.root());
		

//		avl.rotacaoSimplesDireita(avl.root());
		
//		avl.rotacaoSimplesEsquerda(avl.root().getFilhoDireito());
		
// ROTACOES DUPLAS
		
//		
//		avl.rotacaoDuplaDireita(avl.root());
//		avl.rotacaoDuplaEsquerda(avl.root());
		//System.out.println(avl.mostraArvore());
		//System.out.println(avl.mostraFatores());
	}

}