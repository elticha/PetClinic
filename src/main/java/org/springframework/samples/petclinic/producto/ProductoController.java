/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.producto;

import java.util.Map;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;


public class ProductoController {
    private static final String VIEW_PRODUCTOS = "productos/createOrUpdateProductoForm";
    private final ProductoRepository producto;

    public ProductoController(ProductoRepository productos) {
        this.producto = productos;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/producto/new")
    public String initCreationForm(Map<String, Object> model) {
        Producto producto = new Producto();
        model.put("producto", producto);
        return VIEW_PRODUCTOS;
    }

    @PostMapping("/producto/new")
    public String processCreationForm(@Valid Producto producto, BindingResult result) {
        if (result.hasErrors()) {
            return VIEW_PRODUCTOS;
        } else {
            this.producto.save(producto);
            return "redirect:/producto/find";
        }
    }
    
    @GetMapping("/producto/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("producto", new Producto());
        return "productos/findProductos";
    }
}
