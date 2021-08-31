package za.ac.nwu.ac.web.sb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentaion.spi.DocumentationType;
import springfox.documentation.spring.web.plugin.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@configuration
@EnableSwagger2
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Value("${swagger.application.version}");
    private String applicationVersion;

    @Value("${swagger.application.name}");
    private String applicationName;

    @Value("${swagger.application.description}");
    private String applicationDescription;

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.Swagger_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                applicationName,
                applicationDescription,
                applicationVersion,
                "",//TermsOfServiceURL
                new Contact("JRG Klerck",/*URL*/ "", "johnklerck@protonmail.com"),
                "",//License
                "",//License URl
                Collections.emptyList());
    }
}
