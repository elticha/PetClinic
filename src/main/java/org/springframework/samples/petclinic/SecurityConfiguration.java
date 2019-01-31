package org.springframework.samples.petclinic;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author fernando
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    DataSource dataSource;
    
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication().usersByUsernameQuery("select username, password, enabled from usuarios where username=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();
        http.authorizeRequests().antMatchers(
                "/",
                "/welcome",
                "/home",
                "/owners/find",
                "/vets/find",
                "/medicamento/find",
                "/producto/find",
                "/Reportes.html",
                "/registration",
                "owners/{ownerId}/edit",
                "owners/{id}",
                "/users/report")
                .authenticated()
                .and()
                .formLogin()
                .permitAll();
    }
}
