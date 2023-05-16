package arvoreRN;

public class MainArvoreRN {

	public static void main(String[] args) {
        ArvoreRN arn = new ArvoreRN();

        /// teste 1 ///
        arn.insert(null, 1);
        arn.insert(arn.root(), 2);
        arn.insert(arn.root(), 3);
        arn.insert(arn.root(), 4);
        arn.insert(arn.root(), 5);
        arn.insert(arn.root(), 6);
        arn.insert(arn.root(), 7);
        arn.insert(arn.root(), 8);
        arn.insert(arn.root(), 9);
        arn.insert(arn.root(), 10);
        arn.remove(arn.search(10, arn.root()));
        arn.remove(arn.search(9, arn.root()));
        System.out.println(arn.mostraArvore());
        System.out.println("---------------------------------------------------");
        
        arn.remove(arn.search(7, arn.root()));
        arn.remove(arn.search(5, arn.root()));
        System.out.println(arn.mostraArvore());
        System.out.println("---------------------------------------------------");

        arn.remove(arn.search(1, arn.root()));
        System.out.println(arn.mostraArvore());
        System.out.println("---------------------------------------------------");

        arn.remove(arn.search(2, arn.root()));
        System.out.println(arn.mostraArvore());
        System.out.println("---------------------------------------------------");
                
        arn.remove(arn.search(3, arn.root()));
        System.out.println(arn.mostraArvore());
	}

}