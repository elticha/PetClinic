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
     @Column(name = "nombre")
     String nombre;
     @Column(name = "descripcion")
     String descripcion;
     @Column(name = "numero_serie")
     String numero_serie;
     @Column(name = "cantidad")
     String cantidad;
     @Column(name = "precio")
     String precio;
     @Column(name = "files")
     String files;

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }
    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
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
