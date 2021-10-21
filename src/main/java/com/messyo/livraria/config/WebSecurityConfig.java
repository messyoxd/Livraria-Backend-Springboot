package com.messyo.livraria.config;

import com.messyo.livraria.usuario.enums.Role;
import com.messyo.livraria.usuario.service.AuthenticationService;
import com.messyo.livraria.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String USUARIOS_API_URL = "/api/v1/usuarios/**";
    public static final String EDITORAS_API_URL = "/api/v1/editoras/**";
    public static final String LIVROS_API_URL = "/api/v1/livros/**";
    public static final String EMPRESTIMOS_API_URL = "/api/v1/emprestimos/**";
    public static final String H2_CONSOLE_URL = "/h2-console/**";
    public static final String SWAGGER_URL = "/swagger-ui.html";

    public static final String ROLE_ADMIN = Role.ADMIN.getDescription();
    public static final String ROLE_USER = Role.USER.getDescription();

    private static final String[] SWAGGER_RESOURCES = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/**",
            "/**.html",
            "/webjars/**",
    };
    public static final String USUARIOS_API_REGISTER_URL = "/api/v1/usuarios";
    public static final String USUARIOS_API_LOGIN_URL = "/api/v1/usuarios/authenticate";

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final UserDetailsService userDetailsService;
//    private final AuthenticationService authenticationService;

    private final JwtRequestFilter jwtRequestFilter;

//    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests().antMatchers(USUARIOS_API_URL, H2_CONSOLE_URL, SWAGGER_URL).permitAll()
//                .antMatchers(EDITORAS_API_URL, LIVROS_API_URL).hasAnyRole(ROLE_ADMIN)
//                .antMatchers(EMPRESTIMOS_API_URL).hasAnyRole(ROLE_ADMIN, ROLE_USER)
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .and().sessionManagement()
//                .sessionCreationPolicy(STATELESS);
//
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//        http.headers().frameOptions().disable();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(USUARIOS_API_URL, H2_CONSOLE_URL, SWAGGER_URL).permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, USUARIOS_API_REGISTER_URL, USUARIOS_API_LOGIN_URL).permitAll();
        http.authorizeRequests().antMatchers(EDITORAS_API_URL, LIVROS_API_URL).hasAnyRole(ROLE_ADMIN);
        http.authorizeRequests().antMatchers(EMPRESTIMOS_API_URL).hasAnyRole(ROLE_ADMIN, ROLE_USER);
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http.headers().frameOptions().disable();
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(SWAGGER_RESOURCES);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

//    @Override
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider =
//                new DaoAuthenticationProvider();
//        provider.setUserDetailsService(authenticationService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//
//    }
}
