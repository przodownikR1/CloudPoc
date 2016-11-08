package pl.java.scalatech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableAuthorizationServer
@Slf4j
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Value("${spring.application.name}")
    String appName;

    @Value("${security.oauth2.client.clientId}")
    String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    String clientSecret;

    @Value("${security.oauth2.client.scope}")
    String[] scopes;

    @Value("${security.oauth2.client.authorized-grant-types}")
    String[] grantTypes;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);//.userApprovalHandler(userApprovalHandler); 
    }
    
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("isAnonymous() || permitAll()").checkTokenAccess("authenticated");
     // enable '/oauth/check_token' endpoint
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        log.info("++++   clientId : {} , secretId :{} , grantTypes : {} , scopes : {}", clientId, clientSecret, grantTypes, scopes);
        // @formatter:off
            clients
            .inMemory()
            .withClient(clientId)
            .secret(clientSecret)
            .authorizedGrantTypes(grantTypes)
            .scopes(scopes)
            .resourceIds(appName)
            .accessTokenValiditySeconds(300)
            .refreshTokenValiditySeconds(1200)
            
            .and()
            .withClient("jsclient")
                .secret("jspasswd")
                .authorizedGrantTypes("implicit")
                .scopes("read", "write","name","color")
                .resourceIds(appName+"_resourceServer")
                .authorities("USER", "ADMIN")
                .redirectUris("http://localhost:9001/auth/response")
                .accessTokenValiditySeconds(60 * 60 * 24) 
                .autoApprove(true)
            .and()
                .withClient("owner")
                    .secret("passwd")
                    .authorizedGrantTypes("password","refresh_token")
                    .scopes("read", "write","name","color")
                    .resourceIds(appName+1)
                    .authorities("USER", "ADMIN")
                    .redirectUris("http://localhost:9001/auth/response")
                    .accessTokenValiditySeconds(60 * 60 * 24) 
                    .autoApprove(true);
            
            // @formatter:on
            
    }
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
    
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }

    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }
    
}
