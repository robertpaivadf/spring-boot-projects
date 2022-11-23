package io.github.robertpaivadf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class VendasConfig {

    @Bean (name = "applicationName")
    public String applicationName(){
        return "Sistema de Vendas";
    }

    @Bean
    @Profile("dev")
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("Executando o profile dev");
        };
    }

}
