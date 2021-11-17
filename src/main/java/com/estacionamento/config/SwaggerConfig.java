package com.estacionamento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
    @Bean
     public OpenAPI estacionamentoAPI() {
     return new OpenAPI().info(new Info()
     .title("API Estacionamento")
     .description("API desenvolvida para avaliação do 2º bimestre da matéria Desenvolvimento para Servidores II")
     .version("v0.0.1")
     .contact(new Contact()
     .name("Felipe Silva, Rene Padua, Thais Silva").email("felipe.silva428@fatec.sp.gov.br, rene.padua@fatec.sp.gov.br, thais.silva110@fatec.sp.gov.br"))
     .license(new License()
     .name("Apache 2.0").url("http://springdoc.org/%22")));
     }
}
