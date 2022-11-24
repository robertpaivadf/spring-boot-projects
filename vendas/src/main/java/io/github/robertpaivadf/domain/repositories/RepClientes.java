package io.github.robertpaivadf.domain.repositories;

import io.github.robertpaivadf.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepClientes extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeLike(String nome);
    List<Cliente> findByNomeLikeOrIdOrderById(String nome, Integer id);

    boolean existsByNome(String nome);

    void deleteByNome(String nome);

    @Query(value = "select c from Cliente c where c.nome like :nome")
    List<Cliente> encontrarPorNome2(String nome);
    @Query(value = "select c.* from tb_cliente c where c.nome like '%nome%'", nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome);



}
