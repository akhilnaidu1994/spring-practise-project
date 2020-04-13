package com.akn.springbootfirstproject.config;

import com.akn.springbootfirstproject.repository.UserRepository;
import com.akn.springbootfirstproject.service.ApplicationUserDetailsService;
import com.akn.springbootfirstproject.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/rest/signup", "/refresh").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilter(refreshFilter())
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), tokenUtils))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), tokenUtils))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    protected JwtRefreshTokenAuthenticationFilter refreshFilter() throws Exception {
        return new JwtRefreshTokenAuthenticationFilter(authenticationManager(), tokenUtils, userRepository);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(applicationUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/h2-console/**");
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
