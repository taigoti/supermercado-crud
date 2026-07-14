import lombok.*;

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
}