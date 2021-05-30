package ar.edu.unq.desapp.grupoD.backenddesapptp.security;

import ar.edu.unq.desapp.grupoD.backenddesapptp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();*/
        auth
                .userDetailsService(service);//.passwordEncoder(encoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                //.authorizeRequests()
                //.antMatchers(HttpMethod.GET,"/reviews").permitAll()
                //.antMatchers(HttpMethod.GET,"/reviews/{id}").permitAll()
                //.antMatchers(HttpMethod.POST,"/reviews").permitAll()
                //.antMatchers(HttpMethod.PATCH,"/reviews/like/{id}").permitAll()
                //.antMatchers(HttpMethod.PATCH,"/reviews/dislike/{id}").permitAll()
                //.and().headers().frameOptions().sameOrigin()
                //.and().httpBasic()
                //.and().csrf().disable().authorizeRequests().antMatchers("/h2-console").permitAll()
                //.csrf().disable().authorizeRequests().antMatchers("/login").permitAll()
                //.anyRequest().authenticated()

                //EStas sin comentar
                //.authorizeRequests().antMatchers(HttpMethod.GET,"/reviews").hasRole("ADMIN")
                //.and().authorizeRequests().antMatchers(HttpMethod.GET,"/hello").hasRole("USER")
                //.and().authorizeRequests().antMatchers(HttpMethod.GET,"/hello").permitAll()
                //.antMatchers(HttpMethod.GET,"/users").permitAll()
                //.antMatchers(HttpMethod.PATCH,"/users/{username}").permitAll()

               //.and().authorizeRequests().antMatchers(HttpMethod.POST,"/login").permitAll()//comentada
                //.antMatchers(HttpMethod.POST,"/register").permitAll();

                //ACCEDER A LA CONSOLA H2
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")

                .and()
                .cors().disable();

        http.authorizeRequests().antMatchers("/register").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/register")

                .and()
                .cors().disable();

                //AUTENTICACION
                http.csrf().disable().authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                        //.and().formLogin().permitAll()
                        //.and().logout().permitAll()

                .and().
                exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
