/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.producto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import org.springframework.samples.petclinic.model.BaseEntity;
/**
 *
 * @author bodeg
 */
@Entity
@Table(name = "producto")
public class Producto extends BaseEntity{
     @Column(name = "nombre" , nullable = false)
     String nombre;
     @Column(name = "descripcion" , nullable = false)
     String descripcion;
     @Column(name = "numero_serie" , nullable = false)
     String numero_serie;
     @Column(name = "cantidad" , nullable = false)
     String cantidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
     
     
     
}
