package io.github.robertpaivadf.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

//@Entity
//@Table(name = "tb_pedido")//, schema = "vendas")
public class Pedido {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Cliente cliente;
    private LocalDate dataPedido;
    private BigDecimal total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
