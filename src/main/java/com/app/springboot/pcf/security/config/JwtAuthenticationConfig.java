package com.app.springboot.pcf.security.config;

import com.app.springboot.pcf.security.JwtAuthenticationEntryPoint;
import com.app.springboot.pcf.security.JwtAuthenticationProvider;
import com.app.springboot.pcf.security.JwtAuthenticationSuccessHandler;
import com.app.springboot.pcf.security.JwtAuthenticationTokenFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

/**
 * @author Anish Panthi
 */
@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class JwtAuthenticationConfig extends WebSecurityConfigurerAdapter {
    private static final String BASE_URL = "/api/v0.1";

    private JwtAuthenticationEntryPoint unauthorizedHandler;

    private JwtAuthenticationProvider authenticationProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {

        return new ProviderManager(Arrays.asList(authenticationProvider));
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManager());
        authenticationTokenFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and()
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                // permit swagger and signup for all
                .authorizeRequests().antMatchers(BASE_URL + "/user/**",
                BASE_URL + "/messages", "/swagger-ui.html", BASE_URL + "/ping", BASE_URL + "/version")
                .permitAll().and()
                // All others urls must be authenticated (filter for token
                // always fires)
                .authorizeRequests().antMatchers("/api/**").authenticated().and()
                // Call our errorHandler if authentication/authorisation fails
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(SIGNUP_BASE_URL));
        // don't create session
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Custom JWT based security filter
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();
    }

    public JwtAuthenticationConfig(JwtAuthenticationEntryPoint unauthorizedHandler, JwtAuthenticationProvider authenticationProvider) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.authenticationProvider = authenticationProvider;
    }
}