import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    static void main(String[] args) {
        String URL = "jdbc:postgresql://localhost:5432/supermercado";
        String USER = "postgres";
        String PASS = "admin";

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            ProdutoDTA produto = new ProdutoDTA(conn);
            produto.getAll();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
