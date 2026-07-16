import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();

        System.out.println("""
                Digite qual operação CRUD quer realizar:
                1: Create | 2: Read | 3: Update | 4: Delete
                """);

        int op = input.nextInt();

        switch (op) {
            case 1 -> create(produtoDAO);
            case 2 -> read(produtoDAO);
            case 3 -> update(produtoDAO);
            case 4 -> delete(produtoDAO);
            default -> System.out.println("Digite uma opção válida!");
        }
    }

    static void read(ProdutoDAO produto) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o ID do produto que qer visualizar:");
        Integer id = sc.nextInt();

        System.out.println(produto.readProduct(id));
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
        System.out.println("Produto criado com sucesso!");
    }

    static void update(ProdutoDAO produto) {
        Produto p = Produto.builder()
                .nome("Macbook")
                .build();

        produto.updateProduct(1, p);

        System.out.println("Produto atualizado com sucesso!");
    }

    static void delete(ProdutoDAO produto) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o ID do produto que quer deletar:");
        int id = sc.nextInt();

        produto.deleteProduct(id);

        System.out.println("Produto deletado com sucesso!");
    }
}
