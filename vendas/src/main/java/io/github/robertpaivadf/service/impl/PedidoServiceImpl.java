package io.github.robertpaivadf.service.impl;

import io.github.robertpaivadf.domain.entities.Cliente;
import io.github.robertpaivadf.domain.entities.ItemPedido;
import io.github.robertpaivadf.domain.entities.Pedido;
import io.github.robertpaivadf.domain.entities.Produto;
import io.github.robertpaivadf.domain.repositories.RepCliente;
import io.github.robertpaivadf.domain.repositories.RepItensPedido;
import io.github.robertpaivadf.domain.repositories.RepPedido;
import io.github.robertpaivadf.domain.repositories.RepProduto;
import io.github.robertpaivadf.exception.RegraNegocioException;
import io.github.robertpaivadf.rest.dto.ItemPedidoDTO;
import io.github.robertpaivadf.rest.dto.PedidoDTO;
import io.github.robertpaivadf.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final RepPedido repository;
    private final RepCliente repCliente;
    private final RepProduto repProduto;
    private final RepItensPedido repItensPedido;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = repCliente
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        repository.save(pedido);
        repItensPedido.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if(itens.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return itens
                .stream()
                .map( dto ->{
                    Integer idProduto = dto.getProduto();
                    Produto produto = repProduto
                            .findById(idProduto).orElseThrow(
                                    () -> new RegraNegocioException("Código de produto inválido: " + idProduto
                            ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;

                }).collect(Collectors.toList());

    }


}
