/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.producto;


import org.springframework.samples.petclinic.owner.*;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.medicamento.Medicamento;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author bodeg
 */
public interface ProductoRepository extends Repository<Producto, Integer>{
    void save(Producto producto);

    void delete(Producto producto);
}
