import lombok.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Produto {
    private Integer id;
    private String nome;
    private Double preco;
    private Integer estoque;
    private String sku;

    public Produto buildProduto(ResultSet response) {
        try(response) {
            return Produto.builder()
                    .id(response.getInt("id"))
                    .nome(response.getString("nome"))
                    .preco(response.getDouble("preco"))
                    .estoque(response.getInt("estoque"))
                    .sku(response.getString("sku"))
                    .build();
        }

        catch (SQLException e) {
            throw new RuntimeException("Erro no build do produto", e);
        }

    }
}