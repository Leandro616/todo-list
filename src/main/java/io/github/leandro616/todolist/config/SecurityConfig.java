package io.github.leandro616.todolist.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.leandro616.todolist.model.service.UsuarioService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   
   @Autowired
   private UsuarioService usuarioService;

   @Bean
   public AuthenticationManager authenticationManager() throws Exception {
      return super.authenticationManager();
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      Map<String, PasswordEncoder> encoders = new HashMap<>();
      encoders.put("bcrypt", new BCryptPasswordEncoder());
      encoders.put("noop", NoOpPasswordEncoder.getInstance());

      return new DelegatingPasswordEncoder("bcrypt", encoders);
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) 
         throws Exception {

      /* // testar com um usuario em memoria
      auth.inMemoryAuthentication()
         .withUser("fulano")
         .password("123")
         .roles("USER"); */
         
      auth.userDetailsService(usuarioService)
         .passwordEncoder(passwordEncoder());
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
         .cors().and()
         .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
   }
}
