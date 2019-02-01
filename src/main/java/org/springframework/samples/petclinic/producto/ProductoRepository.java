/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.producto;


import org.springframework.samples.petclinic.owner.*;
import java.util.Collection;
import java.util.stream.Stream;
import javax.annotation.Resource;
import javax.validation.Path;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.medicamento.Medicamento;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author bodeg
 */
public interface ProductoRepository extends Repository<Producto, Integer>{
    
    @Query("SELECT producto FROM Producto producto WHERE producto.id =:id")
    @Transactional(readOnly = true)
    Producto findById(@Param("id") Integer id);

    @Query("SELECT DISTINCT producto FROM Producto producto")
    @Transactional(readOnly = true)
    Collection<Producto> buscarProductos();
    
    @Query("SELECT producto FROM Producto producto where producto.nombre LIKE :nombre%")
    @Transactional(readOnly = true)
    Collection<Producto> findByNombre(@Param("nombre") String nombre);
    
    void save(Producto producto);

    void delete(Producto producto);
    
}
