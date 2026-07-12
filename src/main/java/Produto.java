import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int estoque;
    private String sku;
}