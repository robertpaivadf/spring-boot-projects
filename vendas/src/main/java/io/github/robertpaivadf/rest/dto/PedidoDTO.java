package io.github.robertpaivadf.rest.dto;

import io.github.robertpaivadf.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    //DTO = Data Tranfer Object

    @NotNull (message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;
    @NotNull (message = "{campo.total.obrigatorio}")
    private BigDecimal total;
    @NotEmptyList
    private List<ItemPedidoDTO> itens;

}
