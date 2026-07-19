import dao.ProdutoDAO;
import model.Produto;
import java.util.List;
import java.util.Scanner;

public class SupermercadoApplication {
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();
        escolherOperacao();
    }

    static void escolherOperacao() {
        Scanner sc = new Scanner(System.in);

        System.out.println("""
                1: Inserir | 2: Buscar por Id | 3: Buscar todos | 4: Atualizar | 5: Deletar
                Digite a operação que quer realizar:""");

        int op = sc.nextInt();

        switch (op) {
            case 1 -> inserir();
            case 2 -> buscar();
            case 3 -> buscarTodos();
            case 4 -> atualizar();
            case 5 -> deletar();
            default -> {
                System.out.println("Digite uma operação válida!");
                escolherOperacao();
            }
        }
    }

    static void inserir() {}
    static void buscar() {}
    static void buscarTodos() {}
    static void atualizar() {}
    static void deletar() {}
}
