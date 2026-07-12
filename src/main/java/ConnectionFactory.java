import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/supermercado", "postgres", "admin");

            if(conn != null){
                System.out.println("Conectado com sucesso!");
            } else {
                System.out.println("Sem conexão!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
