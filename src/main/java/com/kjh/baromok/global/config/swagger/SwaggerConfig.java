package com.kjh.baromok.global.config.swagger;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private static final String securitySchemeName = "JWT TOKEN";

    @Bean
    public GroupedOpenApi baromokApi(ApiErrorCodeOperationCustomizer apiErrorCodeOperationCustomizer) {
        return GroupedOpenApi.builder()
                .group("Baromok-API-v1")
                .pathsToMatch("/**")
                .displayName("Baromok API 명세서")
                // 1. 전역 설정(Info, Security) 주입
                .addOpenApiCustomizer(createOpenApiCustomizer("Baromok API", "v0.1"))
                // 2. 에러 코드 자동화 로직 주입
                .addOperationCustomizer(apiErrorCodeOperationCustomizer)
                .build();
    }

    /**
     * OpenAPI 객체의 공통 설정을 담당하는 커스텀 로직
     */
    private OpenApiCustomizer createOpenApiCustomizer(String title, String version) {
        return openApi -> {
            openApi.info(new Info().title(title).version(version).description("Baromok API Swagger 명세서입니다."));
            openApi.setServers(List.of(
                    new Server().url("/")
            ));
            openApi.addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
            openApi.schemaRequirement(securitySchemeName, createBearerAuthScheme());
        };
    }

    /**
     * JWT Bearer 인증 스키마 생성
     */
    private SecurityScheme createBearerAuthScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name(securitySchemeName);
    }
}

//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public GroupedOpenApi baromokApi(ApiErrorCodeOperationCustomizer apiErrorCodeOperationCustomizer) {
//        return GroupedOpenApi.builder()
//                .group("Baromok API")
//                .pathsToMatch("/**") // 모든 경로의 API를 이 그룹에 포함
//                .addOperationCustomizer(apiErrorCodeOperationCustomizer) // [핵심] 에러 코드 자동화 로직 등록
//                .build();
//    }
//
//    @Bean
//    public OpenAPI swagger() {
//        Info info = new Info()
//                .title("Baromok API")
//                .description("Baromok API Swagger 명세서입니다.")
//                .version("0.0.1");
//
//        // JWT 인증 설정
//        String securitySchemeName = "JWT TOKEN";
//        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securitySchemeName);
//
//        Components components = new Components()
//                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
//                        .name(securitySchemeName)
//                        .type(SecurityScheme.Type.HTTP)
//                        .scheme("Bearer")
//                        .bearerFormat("JWT"));
//
//        return new OpenAPI()
//                .info(info)
//                .addServersItem(new Server().url("/"))
//                .addSecurityItem(securityRequirement)
//                .components(components);
//    }
//}