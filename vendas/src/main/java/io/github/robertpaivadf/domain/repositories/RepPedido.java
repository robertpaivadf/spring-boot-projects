package io.github.robertpaivadf.domain.repositories;

import io.github.robertpaivadf.domain.entities.Cliente;
import io.github.robertpaivadf.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepPedido extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
