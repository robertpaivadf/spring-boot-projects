package io.github.robertpaivadf.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    //DTO = Data Tranfer Object

    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;

}
