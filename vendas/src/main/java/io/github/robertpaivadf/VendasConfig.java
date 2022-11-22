package io.github.robertpaivadf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VendasConfig {

    @Bean (name = "applicationName")
    public String applicationName(){
        return "Sistema de Vendas";
    }
}
