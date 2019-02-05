/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.producto;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductoController {
    private static String UPLOADED_FOLDER = "C://Users//bodeg//Pictures//Saved Pictures//";
    private static final String VIEW_PRODUCTOS = "productos/createOrUpdateProductoForm";
    private static String direccionUrl = System.getProperty("user.dir")+"//src//main//resources//static//resources//images//";
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
    public String processCreationForm(MultipartFile file, @Valid Producto producto, BindingResult result) throws IOException {
       StringBuilder filesNames = new StringBuilder();
        if (result.hasErrors()) {
            return VIEW_PRODUCTOS;
        } else {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            System.out.println(path.toString());
            
            Path o = Paths.get(path.toString());
            Path d = Paths.get(direccionUrl+file.getOriginalFilename());
            Files.copy(o, d, StandardCopyOption.REPLACE_EXISTING);
            this.producto.save(producto);
            return "redirect:/producto/find";
        }
    }
    
    @GetMapping("/producto/find")
    public String buscarProducto(Map<String, Object> model) {
        model.put("producto", new Producto());
        return "productos/findProductos";
    }
    
    @GetMapping("/producto")
    public String processFindForm(Producto producto, BindingResult result, Map<String, Object> model) {

        if (producto.getNombre() == null) {
            producto.setNombre("");
        }


        Collection<Producto> results = this.producto.findByNombre(producto.getNombre());
        if (results.isEmpty()) {

            result.rejectValue("nombre", "notFound", "not found");
            return "productos/findProductos";
        } else if (results.size() == 1) {
            producto = results.iterator().next();
            return "redirect:/producto/" + producto.getId();
        } else {
           
            model.put("selections", results);
            return "producto/productoList";
        }
    }
    
    
    @GetMapping("/producto/{productoId}")
    public ModelAndView showProducto(@PathVariable("productoId") int productoId) {
        ModelAndView mav = new ModelAndView("productos/productoDetails");
        mav.addObject(this.producto.findById(productoId));
        return mav;
    }
    
    @GetMapping("/producto/{productoId}/edit")
    public String initUpdateProductoForm(@PathVariable("productoId") int productoId, Model model) {
        Producto producto = this.producto.findById(productoId);
        model.addAttribute(producto);
        return VIEW_PRODUCTOS;
    }
    
    @PostMapping("/producto/{productoId}/edit")
    public String processUpdateProductoForm(@Valid Producto producto, BindingResult result, @PathVariable("productoId") int productoId) {
        if (result.hasErrors()) {
            return VIEW_PRODUCTOS;
        } else {
            producto.setId(productoId);
            this.producto.save(producto);
            return "redirect:/producto/{productoId}";
        }
    }
    
    @GetMapping("/producto/{productoId}/delete")
    public String deleteProducto(Producto producto, BindingResult result,@PathVariable("productoId") int productoId){
        producto = this.producto.findById(productoId);
        this.producto.delete(producto);
                
        return "redirect:/producto?nombre=";
    }
    
    @GetMapping("/producto/reports")
    public String listadoDeProductos(Producto producto, BindingResult result, Map<String, Object> model) {
        Collection<Producto> results = this.producto.buscarProductos();
        if (results.isEmpty()) {
            return "productos/findProductos";
        } else {
            model.put("selections", results);
            return "productos/productosReport";
        }
    }
    
    
    
    //----------------------------------------------------------------
 

}
