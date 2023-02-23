package io.github.robertpaivadf.service.impl;

import io.github.robertpaivadf.domain.repositories.RepPedido;
import io.github.robertpaivadf.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private RepPedido repository;

    public PedidoServiceImpl(RepPedido repository) {
        this.repository = repository;
    }

}
