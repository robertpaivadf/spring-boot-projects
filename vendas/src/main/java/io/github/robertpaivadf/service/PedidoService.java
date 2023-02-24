package io.github.robertpaivadf.service;

import io.github.robertpaivadf.domain.entities.Pedido;
import io.github.robertpaivadf.domain.enums.StatusPedido;
import io.github.robertpaivadf.rest.dto.PedidoDTO;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atuStatusPedido(Integer id, StatusPedido statusPedido);

}
