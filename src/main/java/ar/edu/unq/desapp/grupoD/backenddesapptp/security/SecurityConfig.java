package ar.edu.unq.desapp.grupoD.backenddesapptp.security;

import ar.edu.unq.desapp.grupoD.backenddesapptp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf().disable()
                //ACCEDER A LA CONSOLA H2
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .antMatchers("/v2/api-docs","/swagger-resources/configuration/ui", "/swagger-resources",
                        "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/register/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors().configurationSource(configurationCors())
                .and()
                .httpBasic()
                .and().headers().frameOptions().disable()
                .and()
                //.csrf().disable()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


    }
    @Bean
    public UrlBasedCorsConfigurationSource configurationCors(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
