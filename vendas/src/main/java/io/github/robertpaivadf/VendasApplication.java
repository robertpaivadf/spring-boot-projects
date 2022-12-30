package io.github.robertpaivadf;

import ch.qos.logback.core.CoreConstants;
import io.github.robertpaivadf.domain.entities.Cliente;
import io.github.robertpaivadf.domain.entities.Pedido;
import io.github.robertpaivadf.domain.repositories.RepCliente;
import io.github.robertpaivadf.domain.repositories.RepPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    //Teste para salvar um cliente no banco de dados
    @Bean
    public CommandLineRunner init(
            @Autowired RepCliente repCliente,
            @Autowired RepPedido  repPedido
            ){
        return args -> {

            Cliente c1 = new Cliente("Robert");
            repCliente.save(c1);

            Pedido p1 = new Pedido();
            p1.setCliente(c1);
            p1.setDataPedido(LocalDate.now());
            p1.setTotal(BigDecimal.valueOf(100));//
            repPedido.save(p1);

            //depois de implementar o fetch no repositorio de cliente
            Cliente cliente =  repCliente.findClienteFetchPedidos(c1.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());



        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
