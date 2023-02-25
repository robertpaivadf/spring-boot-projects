package io.github.robertpaivadf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        System.out.println("Acesse a documentação desta Api no Swagger em: http://localhost:8080/swagger-ui.html");
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("io.github.robertpaivadf.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Vendas API")
                .description("Api do projeto de Vandas")
                .version("1.0")
                .contact(contact())
                .build();
    }

    private Contact contact(){
        return new Contact("Robert", "http://github.com/robertpaivadf","robertpaiva.df@gmail.com");
    }
}

