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
            repClientes.save(new Cliente("Robert"));
            repClientes.save(new Cliente("Carlos"));
            repClientes.save(new Cliente("Maria"));

            List<Cliente> todosClientes = repClientes.findAll();
            todosClientes.forEach(System.out::println);
            System.out.println("-----------------------------TODOS------------------------------");
            System.out.println();
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " - Atualizado");
                repClientes.save(c);
            });

            todosClientes = repClientes.findAll();
            todosClientes.forEach(System.out::println);
            System.out.println("-----------------------------UPDATE------------------------------");
            System.out.println();

            todosClientes = repClientes.findByNome("Robert - Atualizado");
            todosClientes.forEach(System.out::println);
            System.out.println("-----------------------------BUSCA POR NOME------------------------------");
            System.out.println();

            todosClientes = repClientes.findByNomeLike("Robert");
            todosClientes.forEach(System.out::println);
            System.out.println("-----------------------------BUSCA POR NOME LIKE------------------------------");
            System.out.println();

            todosClientes.forEach(c -> {
                repClientes.delete(c);
            });
            todosClientes = repClientes.findAll();
            todosClientes.forEach(System.out::println);
            System.out.println("-----------------------------DELETA ID=2------------------------------");
            System.out.println();

            boolean existe = repClientes.existsByNome("Maria - Atualizado");
            System.out.println("Existe? " + existe);
            System.out.println("-----------------------------EXISTE------------------------------");
            System.out.println();

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
