package br.edu.imepac.administrativo.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi especialidadesApi() {
        return GroupedOpenApi.builder()
                .group("especialidades")
                .pathsToMatch("/api/especialidades/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Administrativo - Especialidades")
                        .version("v1")
                        .description("Documentação apenas do módulo de Especialidades"));
    }
}
