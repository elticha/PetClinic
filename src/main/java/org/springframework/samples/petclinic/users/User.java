/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Mantenimiento de Sistemas
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 29/01/2018 
 */

package org.springframework.samples.petclinic.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.samples.petclinic.model.BaseEntity;

/**
 * @author David Pérez S.
 */
@Entity
@Table(name = "usuarios")
public class User extends BaseEntity{
    
    @Column(name = "username", nullable=false)
    
    private String username;
    
    @Column(name = "password", nullable=false)
    
    private String password;
    
    @Column(name = "email", nullable=false)
    
    private String email;
    
    @Column(name = "enable")
    private Integer enable;   
}
