package io.github.leandro616.todolist.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {
   
   // configuração web do CorsOrigin
   @Bean
   public FilterRegistrationBean<CorsFilter> corsRegistrationBean() {

      List<String> all = Arrays.asList("*");

      // configurações de acesso do cors
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(all);
      configuration.setAllowedHeaders(all);
      configuration.setAllowedMethods(all);
      configuration.setAllowCredentials(true);

      // setando as urls da api que receberão as configurções do cors
      UrlBasedCorsConfigurationSource source = 
         new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);

      // criando um FilterRegistrationBean
      FilterRegistrationBean<CorsFilter> filter = 
         new FilterRegistrationBean<>(new CorsFilter(source));
      filter.setOrder(Ordered.HIGHEST_PRECEDENCE);

      return filter;
   }
}
