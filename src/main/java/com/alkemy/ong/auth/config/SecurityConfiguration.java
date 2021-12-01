package com.alkemy.ong.auth.config;

<<<<<<< HEAD
import com.alkemy.ong.service.UserDetailsCustomService;
=======
import com.alkemy.ong.auth.service.UserDetailsCustomService;
>>>>>>> feature/segurity
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
<<<<<<< HEAD

=======
>>>>>>> feature/segurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

<<<<<<< HEAD
    // Instanciamos CustomService.
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    // == MUST ===
=======
    @Autowired
    private UserDetailsCustomService customServ;

>>>>>>> feature/segurity
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
<<<<<<< HEAD
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 1) Define our USER DETAILS -> Creados en Service (UserDetailsCustomService)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsCustomService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // 2) Main Configure:
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        // Disable CSRF (no importante para nosotros AHORA)
        httpSecurity.csrf().disable()
                // Permitir All dentro de "/auth/*" - El Resto quedara RESTRINGIDO!!!
                .authorizeRequests().antMatchers("/auth/*").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                // Policy = For Each Endpoint Called, NEW Headers. (Always Request Auth, stateless)
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

    }

=======
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customServ).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/test").authenticated() // TODO: Add Routes
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS); // TODO: Add STATLESS

        // TODO: Filter JWT before AUTH FILTER

    }
>>>>>>> feature/segurity
}
