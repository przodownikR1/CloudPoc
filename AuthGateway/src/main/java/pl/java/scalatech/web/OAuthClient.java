package pl.java.scalatech.web;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.entity.Car;

@RestController
@Slf4j
public class OAuthClient {

    @Value("${API_BASE_URL}")
    private String baseUrl;
    
    private final OAuth2RestOperations restTemplate;
    
    private final MetricRegistry metricRegistry;

    private final  ClientDetailsService clientDetailsService;

    public OAuthClient(MetricRegistry metricRegistry,ClientDetailsService clientDetailsService, OAuth2RestOperations restTemplate) {
        this.metricRegistry = metricRegistry;
        this.clientDetailsService = clientDetailsService;
        this.restTemplate = restTemplate;              
    }

    @GetMapping("/client/{clientId}")
    ClientDetails client(@PathVariable String clientId) {
        ClientDetails cd = clientDetailsService.loadClientByClientId(clientId);
        return cd;
    }
    
    @GetMapping("/clientToken")
    OAuth2AccessToken clientToken() {
        return restTemplate.getAccessToken();
    }
    
    @GetMapping("/details")
    Object details(OAuth2Authentication authentication) {
        return authentication.getUserAuthentication();
    }

    @GetMapping("/user")
    public Principal user(Principal user) {
        Timer t = metricRegistry.timer("retrieveUserInfo");
        Timer.Context c = t.time();
        log.info("++++++++++++++++++++++++++++++++             user : {}", user);
        c.stop();
        return user;
    }

    @RequestMapping("/user2")
    public Object user2(Principal user) {
        OAuth2Authentication authentication = (OAuth2Authentication) user;
        Authentication userAuthentication = authentication.getUserAuthentication();
        return userAuthentication.getPrincipal();
    }

    @Autowired
    private OAuth2ClientContext context;

    @GetMapping("/info")
    String info() {
        Timer t = metricRegistry.timer("retrieveOAuthUserInfo");
        Timer.Context c = t.time();
        OAuth2AccessToken token = context.getAccessToken();
        String tokenValueBeforeDeletion = token.getValue();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        c.stop();
        return "Oauth Token from context : " + tokenValueBeforeDeletion;
    }

    @GetMapping(value = "/response")
    String response() {
        Timer t = metricRegistry.timer("responseTime");
        Timer.Context c = t.time();
        c.stop();
        return "code";
    }

    @GetMapping(value = "/response", params = "code")
    String responseCode(@RequestParam("code") String code) {    
        return "code: " + code;
    }

    @Autowired
    private TokenStore tokenStore;

    @GetMapping("/oauth/revoke-token")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);

            log.info("current token revoke");
            return new ResponseEntity<>(NO_CONTENT);
        }
        return new ResponseEntity<>(NOT_FOUND);

    }

    private void checkResourceOwner(String user, Principal principal) {
        if (principal instanceof OAuth2Authentication) {
            OAuth2Authentication authentication = (OAuth2Authentication) principal;
            if (!authentication.isClientOnly() && !user.equals(principal.getName())) {
                throw new AccessDeniedException(String.format("User '%s' cannot obtain tokens for user '%s'", principal.getName(), user));
            }
        }
    }
    
}
