package io.github.robertpaivadf.domain.repositories;

import io.github.robertpaivadf.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepProduto extends JpaRepository<Produto, Integer> {
}
