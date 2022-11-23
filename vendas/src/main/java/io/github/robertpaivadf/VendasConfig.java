package io.github.robertpaivadf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Dev //anotation personalizada
public class VendasConfig {

    @Bean (name = "applicationName")
    public String applicationName(){
        return "Sistema de Vendas";
    }

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("Executando o profile dev");
        };
    }

}
