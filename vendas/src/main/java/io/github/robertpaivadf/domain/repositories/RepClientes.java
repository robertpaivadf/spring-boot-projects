package io.github.robertpaivadf.domain.repositories;

import io.github.robertpaivadf.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepClientes extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNome(String nome);
}
