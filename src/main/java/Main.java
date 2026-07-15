import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.readProduct(1);
    }

    static void create(ProdutoDAO produto) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o nome do produto: ");
        String nome = sc.nextLine();
        System.out.println("Digite o valor do produto: ");
        double preco = sc.nextDouble();
        System.out.println("Digite a quantidade em estoque: ");
        int estoque = sc.nextInt();
        System.out.println("Digite o SKU: ");
        String sku = sc.next();

        System.out.println("Inserir produto? (s/n)");
        if (sc.next().equals("n")) {
            return;
        }

        produto.createProduct(nome, preco, estoque, sku);
    }

    static void update(ProdutoDAO produto) {
        Produto p = Produto.builder()
                .nome("Macbook")
                .build();

        produto.updateProduct(1, p);
    }
}
