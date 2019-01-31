/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Mantenimiento de Sistemas
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 29/01/2018 
 */

package org.springframework.samples.petclinic.users;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author David Pérez S.
 */
@Entity
@Table(name = "usuarios")
public class User implements Serializable {
    
    @Id
    @Column(name="idusuarios")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    
    @Column(name = "username", nullable=false)
    public String username;
    
    @Column(name = "password", nullable=false)
    public String password;
    
    @Column(name = "email", nullable=false)
    public String email;
    
    @Column(name = "enabled")
    public Integer enabled;   
}
