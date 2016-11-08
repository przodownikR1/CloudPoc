package pl.java.scalatech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("swagger")
//@EnableSwagger2
public class SwaggerConfig {}/*extends WebMvcConfigurerAdapter {                                    
    @Bean
    // @formatter:off
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any()).build()          
          .securitySchemes(securitySchemes())
          .securityContexts(securityContexts());
                           
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
    private List<? extends SecurityScheme> securitySchemes() {
        return Arrays
                .asList(new OAuth("oauth2", Arrays.<AuthorizationScope> asList(new AuthorizationScope("read", "ability to read"),new AuthorizationScope("write", "ability to write")),
                        Arrays.<GrantType> asList(new GrantType("password"))));
    }

    private List<SecurityContext> securityContexts() {
        AuthorizationScope[] authScopes = authScopes();
        SecurityReference securityReference = SecurityReference.builder().reference("oauth2").scopes(authScopes).build();
        return newArrayList(SecurityContext.builder().securityReferences(newArrayList(securityReference)).build());

    }
    private AuthorizationScope[] authScopes() {
        AuthorizationScope[] authScopes = new AuthorizationScope[1];
        authScopes[0] = new AuthorizationScopeBuilder().scope("read").description("ability to read").build();
        return authScopes;
    }
    // @formatter:on
}*/