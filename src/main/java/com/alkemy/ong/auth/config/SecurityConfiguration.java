package com.alkemy.ong.auth.config;

import com.alkemy.ong.auth.filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String ORGANIZATION_URL = "/organization/public";
        final String MEMBER_URL = "/members";
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/register").permitAll()

                .antMatchers("/auth/me").permitAll()
                .antMatchers("/storage/*").hasRole(ROLE_ADMIN)

                //Users_ADMIN
                .antMatchers(HttpMethod.GET, "/users").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, "/users/{id}").hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers(HttpMethod.GET, "/users/{id}").hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers(HttpMethod.DELETE, "/users/{id}").hasRole(ROLE_USER)

                //News
                .antMatchers(HttpMethod.GET, "/news").hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers(HttpMethod.GET, "/news/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, "/news/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, "/news/{id}").hasRole(ROLE_ADMIN)

                //categories
                .antMatchers(HttpMethod.GET, "/categories/by-combo").hasRole(ROLE_USER)
                .antMatchers(HttpMethod.GET, "/categories").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/categories").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/categories/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, "/categories/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, "/categories/{id}").hasRole(ROLE_ADMIN)

                //Testimonials
                .antMatchers(HttpMethod.PUT,"/testimonials/{id}").hasRole(ROLE_ADMIN)

                // Contacts
                .antMatchers(HttpMethod.POST, "/contacts").permitAll()
                .antMatchers(HttpMethod.GET, "/contacts").hasRole(ROLE_ADMIN)

                //Activities
                .antMatchers(HttpMethod.PUT, "/activities/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, "/activities").hasRole(ROLE_ADMIN)

                //Slides
                .antMatchers(HttpMethod.PUT, "/slides").hasRole(ROLE_USER)
                .antMatchers(HttpMethod.POST, "/slides").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/slides/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, "/slides/{id}").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, "/slides/{id}").hasRole(ROLE_ADMIN)

                //Organization
                .antMatchers(HttpMethod.GET, ORGANIZATION_URL).permitAll()
                .antMatchers(HttpMethod.POST, ORGANIZATION_URL).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, ORGANIZATION_URL).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, ORGANIZATION_URL + "/{id}").hasRole(ROLE_ADMIN)

                //Members
                .antMatchers(HttpMethod.POST, MEMBER_URL).hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers(HttpMethod.PUT, MEMBER_URL + "/{id}").hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers(HttpMethod.GET, MEMBER_URL).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, MEMBER_URL + "/{id}").hasRole(ROLE_ADMIN)


                .antMatchers("/api/docs/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
