import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    String URL = "jdbc:postgresql://localhost:5432/supermercado";
    String USER = "postgres";
    String PASS = "admin";

    public Connection getConnection() {
        try{
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e){
            System.out.println("Erro ao conectar com o banco de dados");
        }
        return null;
    }
}
