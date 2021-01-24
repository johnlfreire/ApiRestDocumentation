package com.br.sfb.registrocivil.core;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.br.sfb.registrocivil.api.v1.model.ClienteModel;
import com.br.sfb.registrocivil.domain.entities.Cliente;
import com.fasterxml.classmate.TypeResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
	 @Autowired
	    private TypeResolver typeResolver;
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("V1")
          .select()
          .apis(RequestHandlerSelectors.basePackage( "com.br.sfb.registrocivil.api.v1" ) )      
          .build().directModelSubstitute(Cliente.class, ClienteModel.class)
          .alternateTypeRules(AlternateTypeRules.newRule(
  				typeResolver.resolve(Collection.class,Cliente.class),
  				ClienteModel.class))          
          .apiInfo(apiInfo());
        
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Rest API Simples para estudo")
                .description("Um exemplo de aplicação Spring Boot REST API")
                .version("1.0.0")
                .contact(new Contact("John Lenon", "https://github.com/johnlfreire", "john-jtw@hotmail.com"))
                .build();
    }
    
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}