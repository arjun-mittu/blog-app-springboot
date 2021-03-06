package com.example.demo2.security;


import com.example.demo2.filter.CustomAuthenticationFilter;
import com.example.demo2.filter.CustomAuthorisationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter=new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**","/api/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers("/api/es/**").permitAll();
        http.authorizeRequests().antMatchers("/api/elk/**").permitAll();
        http.authorizeRequests().antMatchers("/api/counter").permitAll();
        http.authorizeRequests().antMatchers("/api/scounter").permitAll();
        http.authorizeRequests().antMatchers("/actuator/**").permitAll();
        //http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/user/**").hasAnyAuthority("ROLE_USER");
        //http.authorizeRequests().antMatchers(HttpMethod.POST,"api/user/save/**").hasAnyAuthority("ROLE_ADMIN");
        //http.authorizeRequests().antMatchers("/api/**").permitAll();
        http.authorizeRequests().antMatchers("/api/users").hasAnyAuthority("ROLE_ADMIN","ROLE_SUPER_ADMIN");
        http.authorizeRequests().antMatchers("/api/blog/delete/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN","ROLE_SUPER_ADMIN");
        http.authorizeRequests().antMatchers("/api/blog/save","/api/blog/update/**").hasAuthority("ROLE_USER");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
