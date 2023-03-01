package io.github.robertpaivadf.domain.repositories;

import io.github.robertpaivadf.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepUsuario extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByUsuario(String usuario);
}
