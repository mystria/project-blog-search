package com.mys.projectblogsearch.v1.config;

import com.mys.projectblogsearch.v1.controller.StandardController;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import java.util.List;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StandardOpenApiConfiguration {

    @Bean
    public GroupedOpenApi standardApi() {

        return GroupedOpenApi.builder()
            .group("Standard API - V1")
            .addOpenApiCustomizer(openApi -> openApi.info(new Info()
                    .version("v1")
                    .title("Standard API"))
                .security(List.of(new SecurityRequirement())))
            .packagesToScan(StandardController.class.getPackageName())
            .build();
    }

}
