package cli;

import dao.ProdutoDAO;
import model.Produto;

import java.util.List;
import java.util.Scanner;

public class MenuCLI {
    private final ProdutoDAO dao = new ProdutoDAO();
    private final Scanner scanner = new Scanner(System.in);

    public void escolherOperacao() {
        System.out.println("""
                1: Inserir | 2: Buscar por Id | 3: Buscar todos | 4: Atualizar | 5: Deletar | 6: Sair
                Digite a operação que quer realizar:""");

        int op = scanner.nextInt();

        switch (op) {
            case 1 -> inserir();
            case 2 -> buscar();
            case 3 -> buscarTodos();
            case 4 -> atualizar();
            case 5 -> deletar();
            case 6 -> {
                System.out.println("Até mais!");
                System.exit(0);
            }
            default -> {
                System.out.println("Digite uma operação válida!");
                escolherOperacao();
            }
        }

        escolherOperacao();
    }

    private void inserir() {
        System.out.println("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.println("Digite o valor do produto: ");
        Double preco = scanner.nextDouble();

        System.out.println("Digite o estoque do produto: ");
        Integer estoque = scanner.nextInt();

        System.out.println("Digite o SKU do produto: ");
        String sku = scanner.next();

        Produto novoProduto = new Produto(nome, preco, estoque, sku);
        dao.inserir(novoProduto);

        System.out.println("Produto criado com sucesso!");
        mostrarProduto(novoProduto);
    }

    private void buscar() {
        System.out.println("Digite o id do produto: ");
        Integer id = scanner.nextInt();

        var p = dao.buscarPorId(id);

        mostrarProduto(p);
    }

    private void buscarTodos() {
        List<Produto> lista = dao.buscarTodos();
        System.out.println("Produtos no banco:");

        lista.forEach(this::mostrarProduto);
    }

    private void atualizar() {
        System.out.println("Digite o id do produto que quer atualizar: ");
        Integer id = scanner.nextInt();
        var produto = dao.buscarPorId(id);

        if(produto == null) {
            System.out.println("Não há produtos com esse ID!");
            return;
        }

        mostrarProduto(produto);

        System.out.println("""
                O que quer atualizar?
                1: Nome | 2: Preço | 3: Estoque | 4: SKU
                """);

        int op = scanner.nextInt();
        switch(op) {
            case 1 -> produto.setNome(scanner.nextLine());
            case 2 -> produto.setPreco(scanner.nextDouble());
            case 3 -> produto.setEstoque(scanner.nextInt());
            case 4 -> produto.setSku(scanner.nextLine());
            default -> System.out.println("Digite uma opção válida!");
        }

        dao.atualizar(produto);
    }

    private void deletar() {
        System.out.println("Digite o id do produto que quer deletar: ");
        Integer id = scanner.nextInt();
        var produto = dao.buscarPorId(id);

        if(produto == null) {
            System.out.println("Não há produtos com esse ID!");
            return;
        }

        mostrarProduto(produto);

        System.out.println("Deseja deletar esse produto? (s/n)");
        String wantDelete = scanner.next();

        if(wantDelete.equalsIgnoreCase("n")) { return; }

        dao.deletar(id);
    }

    private void mostrarProduto(Produto produto) {
        System.out.println(
                "Id: " + produto.getId() +
                " - Nome: " + produto.getNome() +
                " - R$" + produto.getPreco() +
                " - Estoque: " + produto.getEstoque() +
                " - SKU: " + produto.getSku());
    }
}
