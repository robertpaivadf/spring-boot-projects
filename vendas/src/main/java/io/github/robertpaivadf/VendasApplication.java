package io.github.robertpaivadf;

import io.github.robertpaivadf.domain.entities.Cliente;
import io.github.robertpaivadf.domain.repositories.RepCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired RepCliente repCliente){
        return args ->{
            Cliente c = new Cliente(null, "Robert");
            repCliente.save(c);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
