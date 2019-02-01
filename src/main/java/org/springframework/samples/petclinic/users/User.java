/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Mantenimiento de Sistemas
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 29/01/2019 
 */
package org.springframework.samples.petclinic.users;

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
public class User {

    @Id
    @Column(name = "idusuarios")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "enabled")
    private Integer enabled;

    @Column(name = "codigopostal", nullable = false)
    private String codigopostal;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "authority")
    //private Authority authority;

    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public Integer getEnabled() {
        return this.enabled;
    }

    public String getCodigopostal() {
        return this.codigopostal;
    }

//    public Authority getAuthority() {
//        return this.authority;
//    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

//    public void setAuthority(Authority authority) {
//        this.authority = authority;
//    }
}
