package pl.java.scalatech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{


    @Autowired
    private TokenStore tokenStore;
    
    // @formatter:off
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console").authenticated()
        //.and().authorizeRequests().antMatchers("/oauth/token").permitAll() 
        //.and().authorizeRequests().antMatchers("/oauth/authorize").permitAll() 
        .and().authorizeRequests().antMatchers("/health").authenticated()        
        .and().authorizeRequests().antMatchers("/info").authenticated()
        .and().authorizeRequests().antMatchers(HttpMethod.GET, "/one/**")
        .access("#oauth2.hasScope('read')")
        .and().authorizeRequests().antMatchers(HttpMethod.GET, "/two/**")
        .access("#oauth2.hasScope('write') and (hasRole('ROLE_ADMIN') or hasRole('ROLE_USER'))")
        .antMatchers("/metrics/**").anonymous()
        .antMatchers("/mappings/**").anonymous()
        .antMatchers("/shutdown/**").access("#oauth2.hasScope('shutdown')")
        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        
        
        http.headers().disable();
        http.csrf().disable().anonymous().disable();                    
     }
    
    
    @Value("${spring.application.name}")
    String appName;
    
 
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
         resources.tokenStore(tokenStore).resourceId(appName);
    }
    // @formatter:on
    
   

}
