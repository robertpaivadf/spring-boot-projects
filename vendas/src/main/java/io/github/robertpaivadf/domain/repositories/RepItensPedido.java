package io.github.robertpaivadf.domain.repositories;

import io.github.robertpaivadf.domain.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepItensPedido extends JpaRepository<ItemPedido, Integer> {
}
