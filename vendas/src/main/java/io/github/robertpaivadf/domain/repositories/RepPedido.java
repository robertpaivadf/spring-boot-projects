package io.github.robertpaivadf.domain.repositories;

import io.github.robertpaivadf.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepPedido extends JpaRepository<Pedido, Integer> {
}
