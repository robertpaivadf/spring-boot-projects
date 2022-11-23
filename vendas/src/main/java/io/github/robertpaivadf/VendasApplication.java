package io.github.robertpaivadf;

import io.github.robertpaivadf.domain.entities.Cliente;
import io.github.robertpaivadf.domain.repositories.RepClientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    //Teste para salvar um cliente no banco de dados
    @Bean
    public CommandLineRunner init(@Autowired RepClientes repClientes){
        return args -> {
            repClientes.salvar(new Cliente("Robert"));
            repClientes.salvar(new Cliente("Maria"));

            List<Cliente> todosClientes = repClientes.obterTodos();
            todosClientes.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
