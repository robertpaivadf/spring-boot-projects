package io.github.robertpaivadf.service;

import io.github.robertpaivadf.domain.entities.Pedido;
import io.github.robertpaivadf.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
