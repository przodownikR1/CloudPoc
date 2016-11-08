package pl.java.scalatech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@Order(-10)
@EnableWebSecurity
public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {

    // @formatter:off
    @Autowired    
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("przodownik").password("barsecret").roles("USER")
        .and()
        .withUser("bar").password("barsecret").roles("ADMIN", "USER");
    }
    // @formatter:on

    // @formatter:off
   @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
       .formLogin().permitAll()
       .and()
       .logout().logoutSuccessUrl("/login?logout").permitAll()
       .and()
       .authorizeRequests()
       .antMatchers("/metrics").access("#oauth2.hasScope('read')");
       
       http.requestMatchers()
       .antMatchers("/", "/login", "/logout", "/oauth/authorize", "/oauth/confirm_access")
       .and()
       .authorizeRequests().anyRequest().authenticated();               
        http.headers().frameOptions().disable().and().csrf().disable();                         
        http.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());                 
    }
    // @formatter:on
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/response", "/webjars/**", "/images/**", "/resources/**", "/swagger-ui.html", "/v2/api-docs", "/swagger-resources/**", "/auth/v2/api-docs");
    }

 

}