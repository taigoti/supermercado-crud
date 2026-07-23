import cli.MenuCLI;
import dao.ProdutoDAO;

public class SupermercadoApplication {
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();
        MenuCLI menu = new MenuCLI();

        menu.escolherOperacao(dao);
    }
}