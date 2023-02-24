package io.github.robertpaivadf.exception;

public class PedidoNEException extends RuntimeException {
    public PedidoNEException() {
        super("Pedido não Encontrado");
    }
}
