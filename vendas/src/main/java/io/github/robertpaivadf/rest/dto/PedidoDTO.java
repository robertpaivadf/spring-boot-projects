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

    @NotNull (message = "Campo obrigatório cliente")
    private Integer cliente;
    @NotNull (message = "Campo obrigatório total")
    private BigDecimal total;
    @NotEmptyList
    private List<ItemPedidoDTO> itens;

}
