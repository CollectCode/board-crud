package spring_study.board_crud;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
        .group("v1.definition")
        .pathsToMatch("/api/**")
        .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI()  {
        return new OpenAPI()
        .info(new Info().title("BEER_PROJECT API")
        .description("맥주 커뮤니티 프로젝트 API 명세서")
        .version("v0.0.1"));
    }
}
