import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProdutoDTA {
    Statement stm;

    public ProdutoDTA(Connection conn) {
        try {
            this.stm = conn.createStatement();
        }
        catch(SQLException e) {
            this.stm = null;
            System.out.println(e.getMessage());
        }
    }

    public void getAll() {
        selectQuery(this.stm);
    }

    private void selectQuery(Statement stm) {
        try {
            String query = "SELECT * FROM produtos";
            ResultSet response = stm.executeQuery(query);

            while (response.next()) {
                Produto produto = Produto.builder()
                        .id(response.getInt("id"))
                        .nome(response.getString("nome"))
                        .preco(response.getDouble("preco"))
                        .estoque(response.getInt("estoque"))
                        .sku(response.getString("sku"))
                        .build();

                System.out.println(produto);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
