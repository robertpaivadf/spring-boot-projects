package io.github.robertpaivadf.domain.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_produto")//, schema = "vendas")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty (message = "Campo descrição é obrigatório")
    private String descricao;
    @Column(name = "preco_unitario")
    @NotNull (message = "Campo preço não pode ser nulo")
    private BigDecimal preco;

}
