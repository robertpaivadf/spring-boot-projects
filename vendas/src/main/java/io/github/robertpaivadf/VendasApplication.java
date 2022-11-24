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
            repClientes.salvar(new Cliente("Carlos"));
            repClientes.salvar(new Cliente("Maria"));

            List<Cliente> todosClientes = repClientes.obterTodos();
            todosClientes.forEach(System.out::println);
            System.out.println("-----------------------------TODOS------------------------------");
            System.out.println();

            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " - Atualizado");
                repClientes.atualizar(c);
            });
            todosClientes = repClientes.obterTodos();
            todosClientes.forEach(System.out::println);
            System.out.println("-----------------------------UPDATE------------------------------");
            System.out.println();

            todosClientes = repClientes.buscarPorNome("Robert");
            todosClientes.forEach(System.out::println);
            System.out.println("-----------------------------BUSCA POR NOME------------------------------");
            System.out.println();

            repClientes.deletar(2);
            todosClientes = repClientes.obterTodos();
            todosClientes.forEach(System.out::println);
            System.out.println("-----------------------------DELETA ID=2------------------------------");
            System.out.println();

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
