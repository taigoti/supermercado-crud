import dao.ProdutoDAO;
import model.Produto;
import java.util.List;
import java.util.Scanner;

public class SupermercadoApplication {
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();

        escolherOperacao(dao);
    }

    static void escolherOperacao(ProdutoDAO dao) {
        Scanner sc = new Scanner(System.in);

        System.out.println("""
                1: Inserir | 2: Buscar por Id | 3: Buscar todos | 4: Atualizar | 5: Deletar | 6: Sair
                Digite a operação que quer realizar:""");

        int op = sc.nextInt();

        switch (op) {
            case 1 -> inserir(dao);
            case 2 -> buscar(dao);
            case 3 -> buscarTodos(dao);
            case 4 -> atualizar(dao);
            case 5 -> deletar(dao);
            case 6 -> {
                System.out.println("Até mais!");
                System.exit(0);
            }
            default -> {
                System.out.println("Digite uma operação válida!");
                escolherOperacao(dao);
            }
        }

        escolherOperacao(dao);
    }

    static void inserir(ProdutoDAO dao) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do produto: ");
        String nome = sc.nextLine();
        System.out.println("Digite o valor do produto: ");
        Double preco = sc.nextDouble();
        System.out.println("Digite o estoque do produto: ");
        Integer estoque = sc.nextInt();
        System.out.println("Digite o SKU do produto: ");
        String sku = sc.next();

        Produto novoProduto = new Produto(nome, preco, estoque, sku);

        dao.inserir(novoProduto);
        System.out.println("ID gerado pelo banco: " + novoProduto.getId());
    }

    static void buscar(ProdutoDAO dao) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id do produto: ");
        Integer id = sc.nextInt();
        var p = dao.buscarPorId(id);
        System.out.println(p);
    }

    static void buscarTodos(ProdutoDAO dao) {
        List<Produto> lista = dao.buscarTodos();
        System.out.println("Produtos no banco:");
        lista.forEach(p -> System.out.println(p.getNome() + " - R$" + p.getPreco()));
    }

    static void atualizar(ProdutoDAO dao) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id do produto que quer atualizar: ");
        Integer id = sc.nextInt();
        var p = dao.buscarPorId(id);

        if(p == null) {
            System.out.println("Não há produtos com esse ID!");
            return;
        }

        System.out.println(p);
        System.out.println("""
                O que quer atualizar?
                1: Nome | 2: Preço | 3: Estoque | 4: SKU
                """);

        int op = sc.nextInt();
        switch(op) {
            case 1 -> p.setNome(sc.nextLine());
            case 2 -> p.setPreco(sc.nextDouble());
            case 3 -> p.setEstoque(sc.nextInt());
            case 4 -> p.setSku(sc.nextLine());
            default -> System.out.println("Digite uma opção válida!");
        }

        dao.atualizar(p);
    }

    static void deletar(ProdutoDAO dao) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o id do produto que quer deletar: ");
        Integer id = sc.nextInt();
        var p = dao.buscarPorId(id);

        if(p == null) {
            System.out.println("Não há produtos com esse ID!");
            return;
        }

        System.out.println(p);
        System.out.println("Deseja deletar esse produto? (s/n)");
        String wantDelete = sc.next();

        if(wantDelete.equalsIgnoreCase("n")) { return; }

        dao.deletar(id);
    }
}