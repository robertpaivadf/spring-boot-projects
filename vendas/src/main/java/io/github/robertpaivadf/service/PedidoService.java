package io.github.robertpaivadf.service;

import io.github.robertpaivadf.domain.entities.Pedido;
import io.github.robertpaivadf.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

}
