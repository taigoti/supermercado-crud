import java.sql.*;

public class ProdutoDAO {
    private Connection conn = new ConnectionFactory().getConnection();

    public void readProduct() {
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

    public void readProduct(int id) {
        try {
            PreparedStatement query = this.conn
                    .prepareStatement("SELECT * FROM produtos WHERE id = ?");

            query.setInt(1, id);
            query.execute();

            ResultSet response = query.getResultSet();

            if(response.next()) {
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

    public void createProduct(String nome, double preco, int estoque, String sku) {
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

    public void updateProduct(int id, Produto p) {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, estoque = ?, sku = ? WHERE id = ?";

        try (Connection conn = this.conn;
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            PreparedStatement query = this.conn
                    .prepareStatement("SELECT * FROM produtos WHERE id = ?");

            query.setInt(1, id);
            query.execute();

            ResultSet response = query.getResultSet();
            if(response.next()) {
                Produto produto = Produto.builder()
                        .nome(response.getString("nome"))
                        .preco(response.getDouble("preco"))
                        .estoque(response.getInt("estoque"))
                        .sku(response.getString("sku"))
                        .build();

                stmt.setString(1, produto.getNome());
                stmt.setDouble(2, produto.getPreco());
                stmt.setInt(3, produto.getEstoque());
                stmt.setString(4, produto.getSku());
                stmt.setLong(5, 1);
            }
            if (p.getNome() != null) { stmt.setString(1, p.getNome()); }
            if (p.getPreco() != null) { stmt.setDouble(2, p.getPreco()); }
            if (p.getEstoque() != null) { stmt.setInt(3, p.getEstoque()); }
            if (p.getSku() != null) { stmt.setString(4, p.getSku()); }

            stmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conn = this.conn;
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Produto deletado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto", e);
        }
    }
}
