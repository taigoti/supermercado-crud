import dao.ProdutoDAO;
import model.Produto;
import java.util.List;
import java.util.Scanner;

public class SupermercadoApplication {
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();
        //escolherOperacao(dao);
        buscarTodos(dao);
    }

    static void escolherOperacao(ProdutoDAO dao) {
        Scanner sc = new Scanner(System.in);

        System.out.println("""
                1: Inserir | 2: Buscar por Id | 3: Buscar todos | 4: Atualizar | 5: Deletar
                Digite a operação que quer realizar:""");

        int op = sc.nextInt();

        switch (op) {
            case 1 -> inserir(dao);
            case 2 -> buscar(dao);
            case 3 -> buscarTodos(dao);
            case 4 -> atualizar(dao);
            case 5 -> deletar(dao);
            default -> {
                System.out.println("Digite uma operação válida!");
                escolherOperacao(dao);
            }
        }
    }

    static void inserir(ProdutoDAO dao) {
        Produto novoProduto = new Produto("Filtro de Linha Clamper", 350.00, 10, "D09");
        dao.inserir(novoProduto);
        System.out.println("ID gerado pelo banco: " + novoProduto.getId());
    }

    static void buscar(ProdutoDAO dao) {}

    static void buscarTodos(ProdutoDAO dao) {
        List<Produto> lista = dao.buscarTodos();
        System.out.println("Produtos no banco:");
        lista.forEach(p -> System.out.println(p.getNome() + " - R$" + p.getPreco()));
    }

    static void atualizar(ProdutoDAO dao) {
                // --- TESTANDO UPDATE ---
//        novoProduto.setPreco(320.00); // Mudando o preço localmente
//        dao.atualizar(novoProduto);    // Sincronizando com o banco
//
//        dao.deletar(novoProduto.getId());
    }

    static void deletar(ProdutoDAO dao) {}
}