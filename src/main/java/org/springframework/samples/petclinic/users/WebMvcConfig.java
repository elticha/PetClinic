/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Mantenimiento de Sistemas
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 31/01/20189
 */

package org.springframework.samples.petclinic.users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author David Pérez S.
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
