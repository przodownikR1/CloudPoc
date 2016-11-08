package pl.java.scalatech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import lombok.extern.slf4j.Slf4j;

@EnableResourceServer
@Configuration
@Slf4j
public class ResourceServerConfig  extends ResourceServerConfigurerAdapter{


  @Value("${spring.application.name}")
  String appName;
  
 
    // @formatter:off
  @Override
  public void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
      .and().authorizeRequests().antMatchers("/health").access("#oauth2.hasScope('metrics')")
      .and().authorizeRequests().antMatchers("/info").access("#oauth2.hasScope('metrics')")
      .antMatchers(HttpMethod.GET, "/api/user/**","/api/car/**").access("#oauth2.hasScope('read')")
      .antMatchers(HttpMethod.OPTIONS, "/api/user/**","/api/car/**").access("#oauth2.hasScope('read')")
      .antMatchers(HttpMethod.POST, "/api/user/**","/api/car/**").access("#oauth2.hasScope('write')")
      .antMatchers(HttpMethod.PUT, "/api/user/**","/api/car/**").access("#oauth2.hasScope('update')")
      .antMatchers(HttpMethod.PATCH, "/api/user/**","/api/car/**").access("#oauth2.hasScope('write')")
      .antMatchers(HttpMethod.DELETE, "/api/user/**","/api/car/**").access("#oauth2.hasScope('remove')")
      .and().authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/**")
      .access("#oauth2.hasScope('user') and hasRole('ROLE_USER')")
      .and().authorizeRequests().antMatchers(HttpMethod.GET, "/api/car/**")
      .access("#oauth2.hasScope('car') and hasRole('ROLE_USER')")
      .antMatchers("/metrics/**").access("#oauth2.hasScope('metrics')")
      .antMatchers("/mappings/**").access("#oauth2.hasScope('metrics')")
      .antMatchers("/shutdown/**").access("#oauth2.hasScope('shutdown')")
      .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());           
      http.headers().frameOptions().disable();
      http.csrf().disable().anonymous().disable();                    
   }
    // @formatter:on
  
 
    @Autowired
    RemoteTokenServices remoteTokenService;
        
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {       
        resources.resourceId(appName).tokenServices(remoteTokenService);
    }
              
    @Bean("remoteTokenService")   
    public RemoteTokenServices remoteTokenServices(
        final @Value("${auth.server.checkTokenUrl}") String checkTokenUrl,
        final @Value("${auth.server.clientId}") String clientId,
        final @Value("${auth.server.clientSecret}") String clientSecret) {
        log.info("checkUrl : {}  ,  clientId : {}  , clientSecret :  {}",checkTokenUrl,clientId,clientSecret);
        final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(checkTokenUrl);
        remoteTokenServices.setClientId(clientId);
        remoteTokenServices.setClientSecret(clientSecret);
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
        return remoteTokenServices;
    }
    
    @Bean
    public AccessTokenConverter accessTokenConverter() {
        return new DefaultAccessTokenConverter();
    }

}

/*   @Override
public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
   
    RemoteTokenServices tokenService = new RemoteTokenServices();
    tokenService.setClientId("jsclient");        
    tokenService.setClientSecret("jspasswd");
    tokenService.setCheckTokenEndpointUrl("http://localhost:9001/auth/oauth/check_token");

    resources.resourceId(appName+"_resourceServer").tokenServices(tokenService).stateless(false);
    log.info("!!!!!!                    resource :: {}  ",resources);  
}*/