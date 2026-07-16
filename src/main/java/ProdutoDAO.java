import java.sql.*;

public class ProdutoDAO {
    private ConnectionFactory conn = new ConnectionFactory();

    public Produto readProduct(Integer id) {
        String sql = (id == null) ? "SELECT * FROM produtos" : "SELECT * FROM produtos WHERE id = ?";

        try (Connection conn = this.conn.getConnection();
                PreparedStatement query = conn.prepareStatement(sql)) {
            if (id != null) {
                query.setInt(1, id);
            }

            query.execute();
            ResultSet response = query.getResultSet();

            if(response.next()) {
                Produto produto = new Produto();

                return produto.buildProduto(response);
            }
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void createProduct(String nome, double preco, int estoque, String sku) {
        String sql = "INSERT INTO produtos (nome, preco, estoque, sku) VALUES (?, ?, ?, ?)";

        try (Connection conn = this.conn.getConnection();
             PreparedStatement query = conn.prepareStatement(sql)) {
            query.setString(1, nome);
            query.setDouble(2, preco);
            query.setInt(3, estoque);
            query.setString(4, sku);

            query.executeUpdate();
        }

        catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto",e);
        }
    }

    public void updateProduct(int id, Produto p) {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, estoque = ?, sku = ? WHERE id = ?";
        String selectSql = "SELECT * FROM produtos WHERE id = ?";

        try (Connection conn = this.conn.getConnection();
             PreparedStatement query = conn.prepareStatement(sql)) {

            PreparedStatement selectQuery = conn.prepareStatement(selectSql);
            selectQuery.setInt(1, id);
            selectQuery.execute();

            ResultSet response = selectQuery.getResultSet();
            if(response.next()) {
                Produto produto = new Produto();
                produto.buildProduto(response);

                query.setString(1, produto.getNome());
                query.setDouble(2, produto.getPreco());
                query.setInt(3, produto.getEstoque());
                query.setString(4, produto.getSku());
                query.setInt(5, id);
            }

            if (p.getNome() != null) { query.setString(1, p.getNome()); }
            if (p.getPreco() != null) { query.setDouble(2, p.getPreco()); }
            if (p.getEstoque() != null) { query.setInt(3, p.getEstoque()); }
            if (p.getSku() != null) { query.setString(4, p.getSku()); }

            query.executeUpdate();
        }

        catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conn = this.conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.execute();
        }

        catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto", e);
        }
    }
}
