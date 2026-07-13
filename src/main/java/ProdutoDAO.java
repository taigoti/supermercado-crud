import java.sql.*;

public class ProdutoDAO {
    private Connection conn = new ConnectionFactory().getConnection();

    public void viewAll() {
        try {
            PreparedStatement query = this.conn
                    .prepareStatement("SELECT * FROM produtos");

            query.execute();

            ResultSet response = query.getResultSet();

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

            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertProduct(String nome, double preco, int estoque, String sku) {
        String sql = "INSERT INTO produtos (nome, preco, estoque, sku) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement query = this.conn.prepareStatement(sql);

            query.setString(1, nome);
            query.setDouble(2, preco);
            query.setInt(3, estoque);
            query.setString(4, sku);

            query.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto",e);
        }

    }

}
