/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Mantenimiento de Sistemas
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 03/02/2018 
 */

package org.springframework.samples.petclinic.system;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.otros.ComprobadorCodigoPostal;
import org.springframework.samples.petclinic.users.User;
import org.springframework.samples.petclinic.users.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author David Pérez S.
 */
@Controller
public class SeguridadController {
    
    // Parte del Login y el registro de usuarios ********
    @Autowired
    private UserService userService;
    
    @RequestMapping(value={"/","/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/Login");
        return modelAndView;
    }    

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("auth/Registro");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUsername(user.getUsername());
        ComprobadorCodigoPostal cp = new ComprobadorCodigoPostal();
        
        if(user.getUsername().equals("")) {
            bindingResult.rejectValue("username", "error.user", "No puedes dejar el nombre de usuario en blanco");
        } else if (userExists != null) {
            bindingResult.rejectValue("username", "error.user", "Ya existe un usuario con este nombre");
        }
        if (user.getPassword().equals("")) {
            bindingResult.rejectValue("password", "error.user", "No puedes dejar la contraseña en blanco");
        }
        if (user.getEmail().equals("")) {
            bindingResult.rejectValue("email", "error.user", "No puedes dejar el email en blanco");
        }
        if(user.getCodigopostal().equals("")) {
            bindingResult.rejectValue("codigopostal", "error.user", "No puedes dejar el código postal en blanco");
        } else if (!cp.comprobarExisteCodigoPostal(user.getCodigopostal())) {
            bindingResult.rejectValue("codigopostal", "error.user", "Este código postal no existe");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("auth/Registro");
        } else {
            userService.saveUser(user, false);
            modelAndView.addObject("successMessage", "El usuario ha sido registrado exitósamente");
            modelAndView.addObject("textoLogin", "Ir a Login");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("auth/Registro");
        }
        return modelAndView;
    }
}
