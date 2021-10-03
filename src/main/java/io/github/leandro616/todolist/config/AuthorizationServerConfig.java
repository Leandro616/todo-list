package io.github.leandro616.todolist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig 
      extends AuthorizationServerConfigurerAdapter {

   @Autowired
   private AuthenticationManager authenticationManager;

   @Value("${security.jwt.key}")
   private String keyJwt;

   // retorna um token construido pelo jwt
   @Bean
   public TokenStore tokenStore() {
      return new JwtTokenStore(accessTokenConverter());
   }

   @Bean
   public JwtAccessTokenConverter accessTokenConverter() {
      JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
      tokenConverter.setSigningKey(keyJwt);

      return tokenConverter;
   }

   @Override
   public void configure(AuthorizationServerEndpointsConfigurer endpoints) 
         throws Exception {

      endpoints.tokenStore(tokenStore())
         .accessTokenConverter(accessTokenConverter())
         .authenticationManager(authenticationManager);
   }

   // configuração da aplicação cliente
   @Override
   public void configure(ClientDetailsServiceConfigurer clients) 
         throws Exception {
      
      clients.inMemory()
         .withClient("todo-list-app")
         .secret("{noop}@4321@")
         .scopes("read", "write")
         .authorizedGrantTypes("password")
         .accessTokenValiditySeconds(3600);
   }
}
